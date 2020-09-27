import socket
 
"Create a socket and bind to a port "
serverSocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
serverSocket.bind(('localhost', 12000))

"Give the server able to accept at least 10 client connections"
serverSocket.listen(10)

while True:
    "Accept a socket and receive byte from clientSocket"    
    (clientSocket, address) = serverSocket.accept()
    message, address = clientSocket.recvfrom(2048).decode()
    print address
    modifiedMessage = message.upper()
    
    "Send byte to a client socket"    
    serverSocket.sendto(modifiedMessage.encode(), address)

    "Close the server Socket"    
    serverSocket.close()
serverSocket.close()

