from pgmpy.models import BayesianModel
from pgmpy.factors.discrete import TabularCPD
from pgmpy.inference import VariableElimination
import random
import numpy
    
model = BayesianModel([('D', 'G'), ('I', 'G'), ('G', 'L'), ('I', 'S')])

cpd_d = TabularCPD(variable='D', variable_card=2, values=[[0.6],[0.4]])

cpd_i = TabularCPD(variable='I', variable_card=2, values=[[0.7],[0.3]])

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

model.add_cpds(cpd_d, cpd_i, cpd_g, cpd_l, cpd_s)

# grade=g1 | sat=good, grade=1 | sat = 0
infer = VariableElimination(model)
n = 1000
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
