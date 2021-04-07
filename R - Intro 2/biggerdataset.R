# Wczytywanie plików

path_to_files = "C:\\Users\\xsero\\Desktop\\Projekty\\UG\\R - Intro 2\\data"
L = as.list(list.files(path=path_to_files))
L = paste0(path_to_files,"\\",L)
data0 = lapply(L, read.csv)

n = length(data0)

# Wczytanie danych

fulldata = data.frame()

for(i in 1:n) {
  fulldata = rbind(fulldata, subset(data0[[i]], select = c('datetime', 'X350150510')))
}

# Maksima 10min - Wszystkie

full10min = fulldata
full10min$datetime = strptime(full10min$datetime, format = '%Y-%m-%d %H:%M')

# Maksima dzienne - Wszystkie

daily = fulldata
daily$datetime = substr(daily$datetime, 1, 10)
daily = aggregate(daily, by = list(daily$datetime), FUN = max, na.rm=TRUE)
daily = subset(daily, select = c('datetime', 'X350150510'))
colnames(daily)[1] = 'date'
daily$date = strptime(daily$date, format = '%Y-%m-%d')

# Maksima 10min - Pory Roku

wiosna10min = subset(full10min, subset = datetime$mon %in% c(2, 3, 4))
lato10min = subset(full10min, subset = datetime$mon %in% c(5, 6, 7))
jesien10min = subset(full10min, subset = datetime$mon %in% c(8, 9, 10))
zima10min = subset(full10min, subset = datetime$mon %in% c(11, 0, 1))

# Maksima dzienne - Pory Roku

wiosnadaily = subset(daily, subset = date$mon %in% c(2, 3, 4))
latodaily = subset(daily, subset = date$mon %in% c(5, 6, 7))
jesiendaily = subset(daily, subset = date$mon %in% c(8, 9, 10))
zimadaily = subset(daily, subset = date$mon %in% c(11, 0, 1))

# Zapis danych
save(full10min, wiosna10min, lato10min, jesien10min, zima10min, file = "10min.Rdata")
save(daily, wiosnadaily, latodaily, jesiendaily, zimadaily, file = "daily.Rdata")
