import sys
import getopt
import subprocess
from graphviz import Digraph

def purificate(inputfile, outputfile):
    with open(inputfile) as file:
        lines = file.read().splitlines() 
    pos = [str.format("{} {}", line.split()[3], line.split()[4]) for line in lines
           if len(line) != 0 and line[0] == 'p']
    tra = [line.split()[3] for line in lines
           if len(line) != 0 and line[0] == 't']
    edg = [str.format("{} {} {}", line.split()[1], line.split()[2], line.split()[3])
          for line in lines  if len(line) != 0 and line[0] == 'e']


    with open(outputfile, 'w+') as file:
        for elem in pos:
            file.write('p ' + str(elem) + '\n')
        for elem in tra:
            file.write('t ' + str(elem) + '\n')
        for elem in edg:
            file.write('e ' + str(elem) + '\n')

def main():
    inputfile = ''
    outputfile = ''
    limit = 100
    try:
        opts, args = getopt.getopt(sys.argv[1:], "hi:o:l:", ["ifile=", "ofile="])
    except getopt.GetoptError as err:
        print(err)
        print('main.py -i <inputfile.ndr> (no curved edges) -o <outputfile> -l <limit>')
        sys.exit(2)
    if len(opts) == 0:
        print('main.py -i <inputfile.ndr> (no curved edges) -o <outputfile> -l <limit>')
        sys.exit(2)
    for opt, arg in opts:
        if opt == '-h':
            print('main.py -i <inputfile.ndr> (no curved edges) -o <outputfile> -l <limit>')
            sys.exit()
        elif opt in ("-i", "--ifile"):
            inputfile = arg
        elif opt in ("-o", "--ofile"):
            outputfile = arg
        elif opt in ("-l", "--limit"):
            limit = arg
             
    #print('Input file is "', inputfile)
    #print('Output file is "', outputfile)
    purificate(inputfile, str.format("{}{}", outputfile, '.part'))
    # print(str.format("{}{} {}", outputfile, '.part', limit))
    # rm = subprocess.Popen(('rm', str.format("{}{}", outputfile, '.dot')))
    # rm.wait()
    subprocess.call(['./a.out', str.format("{}{}", outputfile, '.part'), str(limit)])
    #print(str.format("{}{} {}", outputfile, '.part', limit))
    # p1.wait()
    subprocess.call(('xdot', str.format("{}{}", outputfile, '.dot')))
    #cmd = str.format("./a.out {}{} {} && xdot {}{}", outputfile, '.part', limit, outputfile, '.dot')
    # subprocess.Popen(cmd)
if __name__ == "__main__":
    main()
