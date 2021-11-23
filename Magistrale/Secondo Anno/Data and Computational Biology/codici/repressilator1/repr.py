import numpy as np
import sys
import matplotlib.pyplot as plt 
import matplotlib.animation as animation
from scipy.integrate import odeint
from matplotlib.animation import FuncAnimation
from IPython import display

def repr(var, time, n):
    alpha = 300
    alpha0 = 0.0005
    beta = 2
    mRNA = var[:3]
    prot = var[3:]
    dmRNA0 = - mRNA[0] + alpha/(1 + prot[2]**n) + alpha0
    dmRNA1 = - mRNA[1] + alpha/(1 + prot[0]**n) + alpha0
    dmRNA2 = - mRNA[2] + alpha/(1 + prot[1]**n) + alpha0
    dprot0 = - beta*(prot[0] - mRNA[0])
    dprot1 = - beta*(prot[1] - mRNA[1])
    dprot2 = - beta*(prot[2] - mRNA[2])
    return [dmRNA0, dmRNA1, dmRNA2, dprot0, dprot1, dprot2]
  
def repressilator_show(n):
    time = np.linspace(0, 100, 5000)
    mRNAInit = np.array([10.0, 1.0, 1.0])
    mprotInit = np.array([1.0, 1.0, 1.0])
    var = np.concatenate((mRNAInit, mprotInit), axis=None)
    result = odeint(repr, var, time, args=(n,))
    
    plt.style.use('seaborn-white')
    plt.subplots(figsize=(18, 5))
    plt.subplot(1, 2, 1)
    plt.plot(time, result[:,0], time, result[:,1], time, result[:,2])
    plt.xlabel('time')
    plt.ylabel('result')
    plt.title('mRNA plot')
    plt.subplot(1, 2, 2)
    plt.plot(time, result[:,3], time, result[:,4], time, result[:,5])
    plt.xlabel('time')
    plt.ylabel('result')
    plt.title('protein plot')
    #plt.savefig("mrna-prot.png",dpi=300)
    plt.show()

    plt.subplots(figsize=(18, 5))
    plt.plot(time[0:2000], result[:,0][0:2000], time[0:2000], result[:,3][0:2000])
    plt.xlabel('time')
    plt.ylabel('result')
    plt.title('delay plot')
    #plt.savefig("delay.png",dpi=300)

    plt.show()


def repressilator(n):
    time = np.linspace(0, 100, 5000)
    mRNAInit = np.array([10.0, 1.0, 1.0])
    mprotInit = np.array([1.0, 1.0, 1.0])
    var = np.concatenate((mRNAInit, mprotInit), axis=None)
    result = odeint(repr, var, time, args=(n,))
    print(result[:, 0])
    for e in result[:, 0]:
        print(e)

def repressilator_anim(n):
    Figure = plt.figure()
    lines_plotted = plt.plot([])   
    line_plotted = lines_plotted[0]
    time = np.linspace(0, 100, 5000)
    mRNAInit = np.array([10.0, 1.0, 1.0])
    mprotInit = np.array([1.0, 1.0, 1.0])
    var = np.concatenate((mRNAInit, mprotInit), axis=None)
    result = odeint(repr, var, time, args=(n,))
    # plt.xlim(0, 100) 
    # plt.ylim(0, max(result[:, 0]))   
    # x = np.linspace(0, 100, 5000)  
    # y = 01
    # y = result[:, 0]
    # line_plotted.set_data((x, y))
    fig = plt.figure()
    ax = plt.axes(xlim=(0, 100), ylim=(0, max(result[:, 0])))
    line, = ax.plot([], [], lw=3)
    anim_created = FuncAnimation(fig,
                                 animate,
                                 # init_func = init,
                                 frames = 100,
                                 fargs = (result, line),
                                 interval = 25)
    # print(anim_created)
    # video = anim_created.to_html5_video()
    # print(video)
    # html = display.HTML(video)
    # display.display(html)
    anim_created.save('./test.gif', writer='imagemagick')
    plt.close()
    
# def AnimationFunction(frame, x, result, line_plotted):
#     y = result[:, 0]
#     line_plotted.set_data((x, y))


def init():
    line.set_data([], [])
    return line,

def animate(i, result, line):
    x = np.linspace(0, 100, 5000)
    y = result[:, 0]
    # print(len(x), len(y))
    line.set_data(x, y)
    return line,


def main():
    repressilator_anim(2)

if __name__ == "__main__":
    main()
