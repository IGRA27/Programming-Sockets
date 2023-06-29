#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>

#define SERV_PORT 5000
#define BUF_SIZE 1024
#define SERV_HOST_ADDR "127.0.0.1"

int main()
{
    int sockfd;
    struct sockaddr_in serv_addr;
    char buffer[BUF_SIZE];

    /*Creacion del socket*/
    sockfd = socket(AF_INET, SOCK_DGRAM, 0); // Notar SOCK_DGRAM para UDP
    if (sockfd == -1)
    {
        fprintf(stderr, "socket creation failed\n");
        return -1;
    }

    /*IP, SERV_PORT, IPV4 */
    serv_addr.sin_family = AF_INET;
    serv_addr.sin_port = htons(SERV_PORT);
    serv_addr.sin_addr.s_addr = inet_addr(SERV_HOST_ADDR);

    strcpy(buffer, "Hello, Server!");
    sendto(sockfd, buffer, BUF_SIZE, 0, (struct sockaddr *)&serv_addr, sizeof(serv_addr));

    close(sockfd);
    return 0;
}
