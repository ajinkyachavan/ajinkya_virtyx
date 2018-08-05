import pandas as pd
import numpy as np
import matplotlib.pylab as plt
from pandas.core import datetools
from statsmodels.tsa.stattools import adfuller

dateparse = lambda x: pd.datetime.strptime(x, "%Y-%m-%dT%H:%M:%S.%fZ")

data = pd.read_csv('timeseries.csv', parse_dates=['time'], index_col='time', date_parser=dateparse)
data = data['value'].iloc[::-1]

f = open("timeseries_output.txt", "w")

f.write("\n")
f.write("min\n\n")
f.write(str(data.resample("5T").min()))
f.write("\n")
f.write("--------------------------")
f.write("\n")

f.write("max\n\n")
f.write(str(data.resample("5T").max()))
f.write("\n")
f.write("--------------------------")
f.write("\n")

f.write("mean\n\n")
f.write(str(data.resample("5T").mean()))
f.write("\n")
f.write("--------------------------")
f.write("\n")

f.write("std\n\n")
f.write(str(data.resample("5T").std()))
f.write("\n")
f.write("--------------------------")
f.write("\n")

f.close()