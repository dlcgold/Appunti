from socket import *

" Define address of the Server" 
serverName = 'localhost'
serverPort = 12033

"Create a socket"
clientSocket = socket(AF_INET, SOCK_DGRAM)
message = input("Insert a text: ")

"Send the message to the udp Server"
clientSocket.sendto(message.encode(), (serverName, serverPort))

"Receive the new message from the udpServer"
modifiedMessage, serverAddress = clientSocket.recvfrom(2048)

print("We have receive " + modifiedMessage.decode() + " bytes")

"Close the udp Client socket"
clientSocket.close()
