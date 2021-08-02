import sys
import re

file_name = sys.argv[1]

print (file_name)
output_file = open("output.csv", "w")
with open(file_name) as f:
    for line in f:
        print ('    ' + line.rstrip())
        
        #Daten mit Punkt
        line = re.sub(';(\d\d).(\d\d).(\d\d\d\d)', r';\2/\1/\3', line)
        
        #Unvollstaendige Daten
        line = re.sub(';(\d\d)/(\d)/(\d\d\d\d)', r';\1/0\2/\3', line)
        line = re.sub(';(\d)/(\d)/(\d\d\d\d)', r';0\1/0\2/\3', line)
        line = re.sub(';(\d)/(\d\d)/(\d\d\d\d)', r';0\1/\2/\3', line)
        line = re.sub(';(\d);', r';01/01/200\1;', line)
        line = re.sub(';(\d\d);', r';01/01/20\1;', line)
        line = re.sub(';(\d\d);', r';01/01/20\1;', line)
        line = re.sub(';(\d\d)/(\d\d);', r';\1/01/20\2;', line)
        
        #Booleans
        line =re.sub(';x;',';1;', line)
        
        print ('    ' + line.rstrip())
        print()
        output_file.write(line)
output_file.close()
