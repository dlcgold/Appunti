from pgmpy.models import BayesianModel
from pgmpy.factors.discrete import TabularCPD
from pgmpy.inference import VariableElimination
import random
import numpy
    
# Defining the model structure. We can define the network by just passing a list of edges.
model = BayesianModel([('D', 'G'), ('I', 'G'), ('G', 'L'), ('I', 'S')])

# Defining individual CPDs.
cpd_d = TabularCPD(variable='D', variable_card=2, values=[[0.6],[0.4]])
cpd_i = TabularCPD(variable='I', variable_card=2, values=[[0.7],[0.3]])

# The representation of CPD in pgmpy is a bit different than the CPD shown in the above picture. In pgmpy the colums
# are the evidences and rows are the states of the variable. So the grade CPD is represented like this:
#
#    +---------+---------+---------+---------+---------+
#    | diff    | intel_0 | intel_0 | intel_1 | intel_1 |
#    +---------+---------+---------+---------+---------+
#    | intel   | diff_0  | diff_1  | diff_0  | diff_1  |
#    +---------+---------+---------+---------+---------+
#    | grade_0 | 0.3     | 0.05    | 0.9     | 0.5     |
#    +---------+---------+---------+---------+---------+
#    | grade_1 | 0.4     | 0.25    | 0.08    | 0.3     |
#    +---------+---------+---------+---------+---------+
#    | grade_2 | 0.3     | 0.7     | 0.02    | 0.2     |
#    +---------+---------+---------+---------+---------+

cpd_g = TabularCPD(variable='G', variable_card=3, 
                   values=[[0.3, 0.05, 0.9,  0.5],
                           [0.4, 0.25, 0.08, 0.3],
                           [0.3, 0.7,  0.02, 0.2]],
                  evidence=['I', 'D'],
                  evidence_card=[2, 2])

cpd_l = TabularCPD(variable='L', variable_card=2, 
                   values=[[0.1, 0.4, 0.99],
                           [0.9, 0.6, 0.01]],
                   evidence=['G'],
                   evidence_card=[3])

cpd_s = TabularCPD(variable='S', variable_card=2,
                   values=[[0.95, 0.2],
                           [0.05, 0.8]],
                   evidence=['I'],
                   evidence_card=[2])

# Associating the CPDs with the network
model.add_cpds(cpd_d, cpd_i, cpd_g, cpd_l, cpd_s)
#print("---------------------------------")
# check_model checks for the network structure and CPDs and verifies that the CPDs are correctly 
# defined and sum to 1.
#model.check_model()
#print(model.check_model())
# print("---------------------------------")

# # We can now call some methods on the BayesianModel object.
# #model.get_cpds()
# print(model.get_cpds())
# print("---------------------------------")

# #model.get_cpds('G')
# print(model.get_cpds('G'))
# print("---------------------------------")


# # Getting the local independencies of a variable.
# #model.local_independencies('G')
# print(model.local_independencies('G'))
# print("---------------------------------")

# # Getting all the local independencies in the network.
# #model.local_independencies(['D', 'I', 'S', 'G', 'L'])
# print(model.local_independencies(['D', 'I', 'S', 'G', 'L']))
# print("---------------------------------")


# # Active trail: For any two variables A and B in a network if any change in A influences the values of B then we say
# #               that there is an active trail between A and B.
# # In pgmpy active_trail_nodes gives a set of nodes which are affected by any change in the node passed in the argument.
# #model.active_trail_nodes('D')
# print(model.active_trail_nodes('D'))
# print("---------------------------------")

# #model.active_trail_nodes('D', observed='G')
# print(model.active_trail_nodes('D', observed='G'))
# print("---------------------------------")


# #Inference by Variable Elimination

# infer = VariableElimination(model)
# print("---------------------------------")

# print(infer.query(['G']))
# # print("---------------------------------")

# print(infer.query(['G'], evidence={'D': 0, 'I': 1}))
# # print("---------------------------------")

# # #cpd_g.values 
# print(cpd_g.values)


# grede=g1|sat=good
# grade=1 | sat = 0
infer = VariableElimination(model)
n = 2000
order = ['D', 'I', 'S', 'G', 'L']
bchoice = [0, 1]
tchoice = [0, 1, 2]
notev = [0, 1, 3, 4]

currd = {'D': random.choice(bchoice),
         'I': random.choice(bchoice),
         'S': 0,
         'G': random.choice(tchoice),
         'L': random.choice(bchoice)}
iterations = int(n/4)
samplesgood = 0
samples = 0


for i in range(0, iterations):
    for ne in notev:
        # get markov blanket
        mb = model.get_markov_blanket(order[ne])
        # get evidence of markov balnker
        evid = dict((var, currd[var]) for var in mb)
        # query 
        que = infer.query([order[ne]], evidence=evid, show_progress=False)     
        # random number
        ran = random.random()
        res = 0
        # sort query result
        tmp = [(elem, ind) for ind, elem in enumerate(que.values)]
        tmp.sort(reverse=True)
        # understand new value for variabile
        acc = 0
        for elem in tmp:
            acc += elem[0]
            if ran < acc:
                res = elem[1]
                break
        # update variable in sample and counters
        currd[order[ne]] = res
        samples += 1
        if currd['G'] == 1:
            samplesgood += 1


finalgood = float(samplesgood / n)
finalbad = float((n - samplesgood) / n)
finalresult = f'P(G=1 | S=0) = <{finalgood},{finalbad}>'
print(finalresult)
