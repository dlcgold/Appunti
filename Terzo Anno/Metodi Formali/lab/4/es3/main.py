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
    try:
        opts, args = getopt.getopt(sys.argv[1:], "hi:o:", ["ifile=", "ofile="])
    except getopt.GetoptError as err:
        print(err)
        print('main.py -i <inputfile.ndr> (no curved edges) -o <outputfile>')
        sys.exit(2)
    if len(opts) == 0:
        print('main.py -i <inputfile.ndr> (no curved edges) -o <outputfile>')
        sys.exit(2)
    for opt, arg in opts:
        if opt == '-h':
            print('main.py -i <inputfile.ndr> (no curved edges) -o <outputfile>')
            sys.exit()
        elif opt in ("-i", "--ifile"):
            inputfile = arg
        elif opt in ("-o", "--ofile"):
            outputfile = arg
    #print('Input file is "', inputfile)
    #print('Output file is "', outputfile)
    purificate(inputfile, str.format("{}{}", outputfile, '.part'))
    subprocess.call(('./a.out', str.format("{}{}", outputfile, '.part')))
    #subprocess.call(('rm', str.format("{}{}", outputfile, '.part')))
    subprocess.call(('xdot', str.format("{}{}", outputfile, '.dot')))
if __name__ == "__main__":
    main()
