import plotly.figure_factory as ff
import numpy as np


class Task:
    startTime = 0
    endTime = 0

    def __init__(self, id, duration=None, m1=None, m2=None, m3=None):
        self.id = id
        self.m1 = m1
        self.m2 = m2
        self.m3 = m3
        if(m1):
            self.duration = m1 + m2 + m3
            if(m1+m2 <= m2+m3):
                self.n = 1
                self.n_val = m1 + m2
            else:
                self.n = 2
                self.n_val = m2 + m3
        else:
            self.duration = duration


class Network:
    gantt = []
    tasks = []

    def __init__(self):
        self.gantt = [[] for x in range(3)]

    def checkDom(self):
        m1Dom = [t.m1 >= t.m2 for t in self.tasks]
        m3Dom = [t.m3 >= t.m2 for t in self.tasks]
        self.isDominated = all(m1Dom) or all(m3Dom)
        if self.isDominated:
            if all(m1Dom):
                self.dom = 1
            else:
                self.dom = 3

    def getNs(self):
        n1 = [t for t in self.tasks if t.n == 1]
        n2 = [t for t in self.tasks if t.n == 2]
        n1.sort(key=lambda x: x.n_val)
        n2.sort(key=lambda x: x.n_val, reverse=True)
        self.tasks = n1 + n2

    def splitTasks(self):
        newtasks = []
        for t in self.tasks:
            newtasks.append(Task(t.id, t.m1))
            newtasks.append(Task(t.id, t.m2))
            newtasks.append(Task(t.id, t.m3))
        self.tasks = newtasks

    def placeTasks(self):
        i = 0
        for t in self.tasks:
            if not self.gantt[i]:
                if i == 0:
                    t.startTime = 0
                else:
                    t.startTime = self.gantt[i-1][0].endTime
                t.endTime = t.startTime+t.duration
            else:
                if i == 0:
                    t.startTime = self.gantt[i][-1].endTime
                else:
                    t.startTime = max(
                        [self.gantt[i-1][-1].endTime, self.gantt[i][-1].endTime])
                t.endTime = t.startTime+t.duration
            self.gantt[i].append(t)
            if i == 2:
                i = 0
            else:
                i += 1

    def drawGantt(self):
        df = []
        for num, proc in enumerate(self.gantt):
            for task in proc:
                df.append(dict(Task=str(num), Start=str(
                    task.startTime), Finish=str(task.endTime), Description="Z"+str(task.id), Resource=str(task.id)))
        fig = ff.create_gantt(df, group_tasks=True, index_col="Resource")
        fig.update_layout(xaxis_showgrid=True)
        fig.update_yaxes(title_text='Processor')
        fig['layout']['xaxis']['tickvals'] = np.arange(
            0, max([t.endTime for t in self.tasks]))
        fig.layout.xaxis.rangeselector = None
        fig.layout.xaxis.type = 'linear'
        fig.show()


# region
network = Network()

z1 = Task(1, m1=4, m2=1, m3=3)
z2 = Task(2, m1=3, m2=3, m3=5)
z3 = Task(3, m1=5, m2=2, m3=4)
z4 = Task(4, m1=6, m2=1, m3=4)
z5 = Task(5, m1=3, m2=2, m3=4)

network.tasks.extend([z1, z2, z3, z4, z5])
# endregion

network.getNs()
network.checkDom()
if network.isDominated:
    network.splitTasks()
    network.placeTasks()
    network.drawGantt()
