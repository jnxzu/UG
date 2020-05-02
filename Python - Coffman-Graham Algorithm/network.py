import plotly.figure_factory as ff
import numpy as np


class Task:
    startTime = 0
    endTime = 0
    id = 0

    def __init__(self, myId):
        self.myId = myId
        self.followingTasks = []
        self.requiredTasks = []


class Network:
    tasks = []
    gantt = []

    def __init__(self, processors):
        self.procs = processors

    def addTask(self, task):
        if(self.tasks.count(task)):
            print("network already contains task")
        self.tasks.append(task)

    def addConnection(self, taskA, taskB):
        taskA.followingTasks.append(taskB)
        taskB.requiredTasks.append(taskA)

    def coffman(self):
        for task in reversed(self.tasks):
            if not task.followingTasks:
                task.id = 1
                break
        for i in range(2, len(self.tasks)+1):
            r = [t for t in self.tasks if not [
                ft for ft in t.followingTasks if ft.id is 0] and t.id is 0]
            for task in r:
                if task.followingTasks[0].id is min([[ft.id for ft in t.followingTasks][0] for t in r]):
                    task.id = i
                    break
        self.tasks.sort(key=lambda x: x.id, reverse=True)

    def graham(self):
        self.gantt = [[0 for i in self.tasks] for j in range(self.procs)]
        for task in self.tasks:
            reqTaskIndexes = []
            for proc in self.gantt:
                for t in proc:
                    if t in task.requiredTasks:
                        reqTaskIndexes.append(proc.index(t))
            if not reqTaskIndexes:
                reqTaskIndexes.append(-1)
            skip = 0
            for i in range(max(reqTaskIndexes)+1, len(proc)):
                for proc in self.gantt:
                    if proc[i] is 0:
                        task.startTime = i
                        task.endTime = i+1
                        proc[i] = task
                        skip = 1
                        break
                if skip is 1:
                    break

    def graph(self):
        self.coffman()
        self.graham()
        df = []
        for num, proc in enumerate(self.gantt):
            for task in proc:
                if task is 0:
                    continue
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


network = Network(2)

t1 = Task(1)
t2 = Task(2)
t3 = Task(3)
t4 = Task(4)
t5 = Task(5)
t6 = Task(6)
t7 = Task(7)
t8 = Task(8)
t9 = Task(9)

network.addTask(t1)
network.addTask(t2)
network.addTask(t3)
network.addTask(t4)
network.addTask(t5)
network.addTask(t6)
network.addTask(t7)
network.addTask(t8)
network.addTask(t9)

network.addConnection(t1, t3)
network.addConnection(t1, t4)
network.addConnection(t1, t5)
network.addConnection(t2, t6)
network.addConnection(t5, t7)
network.addConnection(t4, t7)
network.addConnection(t3, t7)
network.addConnection(t6, t8)
network.addConnection(t7, t9)
network.addConnection(t8, t9)

network.graph()
