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

z1 = Task(1, 1)
z2 = Task(2, 1)
z3 = Task(3, 1)
z4 = Task(4, 1)

network.addTask(z1)
network.addTask(z2)
network.addTask(z3)
network.addTask(z4)

network.addConnection(z1, z2)
network.addConnection(z3, z4)
# endregion

network.getRatings()
network.placeTasks()
network.drawGantt()
