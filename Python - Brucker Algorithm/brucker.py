import plotly.figure_factory as ff
import numpy as np


class Task:
    startTime = 0
    endTime = 0
    rating = 0
    done = False

    def __init__(self, id, deadline):
        self.id = id
        self.deadline = deadline
        self.followingTasks = []
        self.requiredTasks = []


class Network:
    tasks = []
    gantt = []

    def __init__(self, n):
        self.gantt = [[] for x in range(n)]

    def addTask(self, task):
        if(self.tasks.count(task)):
            print("network already contains task")
        self.tasks.append(task)

    def addConnection(self, taskA, taskB):
        if(taskA.id == taskB.id):
            print("cannot connect to self")
        else:
            taskA.followingTasks.append(taskB)
            taskB.requiredTasks.append(taskA)

    def getRatings(self):
        for t in self.tasks:
            if t.followingTasks:
                t.rating = max(
                    (t.followingTasks[0].rating + 1, 1-t.deadline))
            else:
                t.rating = 1-t.deadline

    def placeTasks(self):
        for t in self.tasks:
            if not t.followingTasks:
                final_task = t
        while not final_task.done:
            available_tasks = []
            for t in self.tasks:
                if not [rt for rt in t.requiredTasks if not rt.done] and not t.done:
                    available_tasks.append(t)
            available_tasks.sort(key=lambda x: (
                x.rating, len(x.requiredTasks)), reverse=True)
            i = 0
            for t in available_tasks:
                if i == len(self.gantt):
                    break
                t.startTime = len(self.gantt[i])
                t.endTime = t.startTime + 1
                self.gantt[i].append(t)
                t.done = True
                i += 1

    def drawGantt(self):
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


# region
network = Network(4)

z1 = Task(1, 16)
z2 = Task(2, 20)
z3 = Task(3, 4)
z4 = Task(4, 3)
z5 = Task(5, 15)
z6 = Task(6, 14)
z7 = Task(7, 17)
z8 = Task(8, 6)
z9 = Task(9, 6)
z10 = Task(10, 4)
z11 = Task(11, 10)
z12 = Task(12, 8)
z13 = Task(13, 9)
z14 = Task(14, 7)
z15 = Task(15, 10)
z16 = Task(16, 9)
z17 = Task(17, 10)
z18 = Task(18, 8)
z19 = Task(19, 2)
z20 = Task(20, 3)
z21 = Task(21, 6)
z22 = Task(22, 5)
z23 = Task(23, 4)
z24 = Task(24, 11)
z25 = Task(25, 12)
z26 = Task(26, 9)
z27 = Task(27, 10)
z28 = Task(28, 8)
z29 = Task(29, 7)
z30 = Task(30, 5)
z31 = Task(31, 3)
z32 = Task(32, 5)

network.addTask(z1)
network.addTask(z2)
network.addTask(z3)
network.addTask(z4)
network.addTask(z5)
network.addTask(z6)
network.addTask(z7)
network.addTask(z8)
network.addTask(z9)
network.addTask(z10)
network.addTask(z11)
network.addTask(z12)
network.addTask(z13)
network.addTask(z14)
network.addTask(z15)
network.addTask(z16)
network.addTask(z17)
network.addTask(z18)
network.addTask(z19)
network.addTask(z20)
network.addTask(z21)
network.addTask(z22)
network.addTask(z23)
network.addTask(z24)
network.addTask(z25)
network.addTask(z26)
network.addTask(z27)
network.addTask(z28)
network.addTask(z29)
network.addTask(z30)
network.addTask(z31)
network.addTask(z32)

network.addConnection(z32, z31)
network.addConnection(z31, z29)
network.addConnection(z30, z29)
network.addConnection(z29, z28)
network.addConnection(z28, z27)
network.addConnection(z27, z26)
network.addConnection(z26, z25)
network.addConnection(z25, z24)
network.addConnection(z23, z22)
network.addConnection(z22, z21)
network.addConnection(z20, z19)
network.addConnection(z21, z18)
network.addConnection(z19, z18)
network.addConnection(z18, z17)
network.addConnection(z17, z16)
network.addConnection(z16, z15)
network.addConnection(z15, z11)
network.addConnection(z14, z13)
network.addConnection(z13, z12)
network.addConnection(z10, z9)
network.addConnection(z9, z8)
network.addConnection(z8, z7)
network.addConnection(z7, z6)
network.addConnection(z6, z1)
network.addConnection(z11, z5)
network.addConnection(z24, z5)
network.addConnection(z12, z5)
network.addConnection(z5, z2)
network.addConnection(z4, z3)
network.addConnection(z3, z1)
network.addConnection(z2, z1)
# endregion

network.getRatings()
network.placeTasks()
network.drawGantt()
