from socket import *

"Define the server Port"
serverPort = 12033
serverSocket = socket(AF_INET, SOCK_DGRAM)#Create a socket
serverSocket.bind(('', serverPort))#Bind a socket to a port
print("Server is ready to receive")

#Loop where client can contact the server
while 1:
    " Receive a message from client Socket"
    message, clientAddress = serverSocket.recvfrom(2048)
    lengthMessage = str(len(message.decode()))
    "Send a message to a client socket"    
    serverSocket.sendto(lengthMessage.encode(), clientAddress)
    "Close a socket "
    serverSocket.close()
serverSocket.close()

