#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/socket.h>
#include <netinet/in.h>

#define SERV_PORT 5000
#define BUF_SIZE 1024
#define SERV_HOST_ADDR "127.0.0.1"

int main()
{
    int sockfd;
    struct sockaddr_in serv_addr, cli_addr;
    char buffer[BUF_SIZE];
    socklen_t addr_len;

    /*Creacion */
    sockfd = socket(AF_INET, SOCK_DGRAM, 0); // Notar SOCK_DGRAM para UDP
    if (sockfd == -1)
    {
        fprintf(stderr, "socket creation failed\n");
        return -1;
    }

    /*Asignacion*/
    serv_addr.sin_family = AF_INET;
    serv_addr.sin_port = htons(SERV_PORT);
    serv_addr.sin_addr.s_addr = htonl(INADDR_ANY);

    /*bind*/
    if (bind(sockfd, (struct sockaddr *)&serv_addr, sizeof(serv_addr)) != 0)
    {
        fprintf(stderr, "socket bind failed\n");
        return -1;
    }

    addr_len = sizeof(cli_addr);
    recvfrom(sockfd, buffer, BUF_SIZE, 0, (struct sockaddr *)&cli_addr, &addr_len);
    printf("Message from client: %s\n", buffer);

    close(sockfd);
    return 0;
}
