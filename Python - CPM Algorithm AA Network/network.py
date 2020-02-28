import plotly.figure_factory as ff
import numpy as np


class Task:
    startTime = 0
    lateStartTime = 0
    endTime = 0
    lateEndTime = 0

    def __init__(self, id, duration):
        self.id = id
        self.duration = duration
        self.followingTasks = []
        self.requiredTasks = []

    def __str__(self):
        return "\t\tTASK " + str(task.id) + "\n"+"earliest start: " + str(task.startTime) + "\nearliest end: " + str(task.endTime) + "\nlatest start: " + str(task.lateStartTime) + "\nlatest end: " + \
            str(task.lateEndTime) + "\n"


class Network:
    tasks = []
    gantt = []

    def addTask(self, task):
        if(self.tasks.count(task)):
            print("network already contains task")
        self.tasks.append(task)

    def addConnection(self, taskA, taskB):
        if(taskA.id == taskB.id):
            print("cannot connect to self")
        elif(taskA.id > taskB.id):
            print("lower id task cannot follow higher id task")
        else:
            taskA.followingTasks.append(taskB)
            taskB.requiredTasks.append(taskA)

    def calcTimes(self):
        for task in self.tasks:
            if(len(task.requiredTasks) != 0):
                task.startTime = max([t.endTime for t in task.requiredTasks])
            task.endTime = task.startTime + task.duration
        for task in reversed(self.tasks):
            if(len(task.followingTasks) != 0):
                task.lateEndTime = min(
                    [t.startTime for t in task.followingTasks])
                task.lateStartTime = task.lateEndTime - task.duration
            else:
                task.lateEndTime = task.endTime
                task.lateStartTime = task.startTime

    def delegate(self):
        for task in self.tasks:
            if(len(self.gantt) == 0):
                newproc = []
                newproc.append(task)
                self.gantt.append(newproc)
            else:
                for proc in self.gantt:
                    addnew = True
                    if(len(proc) == 0 or proc[-1].endTime <= task.startTime):
                        proc.append(task)
                        addnew = False
                        break
                if(addnew):
                    newproc = []
                    newproc.append(task)
                    self.gantt.append(newproc)

    def graph(self):
        self.delegate()
        df = []
        for num, proc in enumerate(self.gantt):
            for task in proc:
                df.append(dict(Task=str(num), Start=str(
                    task.startTime), Finish=str(task.endTime), Description="Z"+str(task.id)))
        fig = ff.create_gantt(df, group_tasks=True)
        fig.update_layout(xaxis_showgrid=True)
        fig.update_yaxes(title_text='Processor')
        fig['layout']['xaxis']['tickvals'] = np.arange(
            0, max([t.endTime for t in self.tasks]))
        fig.layout.xaxis.rangeselector = None
        fig.layout.xaxis.type = 'linear'
        fig.show()


network = Network()

t1 = Task(1, 3)
t2 = Task(2, 8)
t3 = Task(3, 2)
t4 = Task(4, 2)
t5 = Task(5, 4)
t6 = Task(6, 6)
t7 = Task(7, 9)
t8 = Task(8, 2)
t9 = Task(9, 1)
t10 = Task(10, 2)
t11 = Task(11, 1)
t12 = Task(12, 2)
t13 = Task(13, 6)
t14 = Task(14, 5)
t15 = Task(15, 9)
t16 = Task(16, 6)
t17 = Task(17, 2)
t18 = Task(18, 5)
t19 = Task(19, 3)

network.addTask(t1)
network.addTask(t2)
network.addTask(t3)
network.addTask(t4)
network.addTask(t5)
network.addTask(t6)
network.addTask(t7)
network.addTask(t8)
network.addTask(t9)
network.addTask(t10)
network.addTask(t11)
network.addTask(t12)
network.addTask(t13)
network.addTask(t14)
network.addTask(t15)
network.addTask(t16)
network.addTask(t17)
network.addTask(t18)
network.addTask(t19)

network.addConnection(t1, t4)
network.addConnection(t1, t5)
network.addConnection(t2, t9)
network.addConnection(t2, t10)
network.addConnection(t3, t6)
network.addConnection(t3, t7)
network.addConnection(t4, t8)
network.addConnection(t5, t9)
network.addConnection(t5, t10)
network.addConnection(t6, t9)
network.addConnection(t6, t10)
network.addConnection(t7, t11)
network.addConnection(t7, t12)
network.addConnection(t8, t13)
network.addConnection(t9, t13)
network.addConnection(t10, t14)
network.addConnection(t10, t15)
network.addConnection(t10, t16)
network.addConnection(t11, t14)
network.addConnection(t11, t15)
network.addConnection(t11, t16)
network.addConnection(t12, t17)
network.addConnection(t13, t18)
network.addConnection(t14, t18)
network.addConnection(t16, t19)
network.addConnection(t17, t19)

network.calcTimes()
network.graph()
for task in network.tasks:
    print(task)
