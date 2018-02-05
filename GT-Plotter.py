from avro.datafile import DataFileReader
from avro.io import DatumReader
from functools import reduce
import pandas as pd
import os
import matplotlib.pyplot as plt
import numpy as np

def get_avro_reader(file_name):
    return DataFileReader(open(file_name, "rb"), DatumReader())

def populate_map_with_count(attack):
    return (attack['country'],int(attack['count']))

def build_attack_map(attack):
    attackMap = dict(map(populate_map_with_count,attack['attackByCountry']))
    attackMap['year'] = attack['year']
    return attackMap

def get_attackdetails_from_avro(file_name):
    reader = get_avro_reader(file_name)
    attacks = list(map(build_attack_map,reader))
    reader.close()
    return attacks;

def get_file_name_list(target_dir):
    file_list = os.listdir(target_dir)
    file_name_list = [target_dir+file for file in file_list if(file.find(".avro") >= 0)]
    return file_name_list

def build_dataframe(attacks):
    df = pd.DataFrame(attacks).set_index('year')
    #df.drop(labels='iyear',axis=0,inplace=True)
    #df.drop(labels='country_txt',axis=1,inplace=True)
    return df

"""
def plot(attacks):
    for attack in attacks:
        group_data = np.array(list(attack.values()))
        group_names = list(attack.keys())
        print(group_data)
        print(group_names)
        fig = plt.figure()
        ax = fig.add_subplot(111)
        #ax.bar(group_names,group_data,0.5)
        ax.barh(group_names, group_data)
        """
        
def main(target_dir):
    file_name_list = get_file_name_list(target_dir)
    attacks = reduce(lambda l1=[],l2=[]:l1+l2,map(get_attackdetails_from_avro,file_name_list))
    df = build_dataframe(attacks)
    cols = df.columns
    indexes = df.index
    for i in list(range(0,len(indexes))):
        plt.figure()
        plt.axhline(0, color='k')
        pd.DataFrame(df.iloc[i]).fillna(value=0).plot(kind='bar', stacked=True)
        
main("/Users/vm033450/Code/Other_Interests/DS-course/GT-Analyser-dataset/" )
