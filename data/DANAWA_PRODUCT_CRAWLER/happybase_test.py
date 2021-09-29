import happybase
connection = happybase.Connection('j5checklist.p.ssafy.io', 9090) #hostname = IP address, port=9090
print(connection.tables())