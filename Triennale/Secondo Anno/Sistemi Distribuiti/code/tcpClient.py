import socket

"Define the server's address"
serverName = 'localhost'
serverPort = 12000

"Create a socket and try the connection to server address"
clientSocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
clientSocket.connect((serverName, serverPort))

message = input("Inserisci un testo: ")
"Send data to server"
clientSocket.sendto(message.encode(), (serverName, serverPort))

"Receive data from server socket"
modifiedMessage, address = clientSocket.recvfrom(2048)
print("I have receive from the server the text" + modifiedMessage.decode())

"Close the client socket"
clientSocket.close()

