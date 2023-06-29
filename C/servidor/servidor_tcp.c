#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>

#define SERV_PORT 5000
#define BUF_SIZE 1024
#define SERV_HOST_ADDR "127.0.0.1"

int main()
{
    int sockfd, connfd;
    struct sockaddr_in serv_addr, cli_addr;
    char buffer[BUF_SIZE];
    socklen_t addr_len;

    /*Creacion socket */
    sockfd = socket(AF_INET, SOCK_STREAM, 0); // Notar SOCK_STREAM para TCP
    if (sockfd == -1)
    {
        fprintf(stderr, "socket creation failed\n");
        return -1;
    }

    //IP, SERV_PORT, IPV4 */
    serv_addr.sin_family = AF_INET;
    serv_addr.sin_port = htons(SERV_PORT);
    serv_addr.sin_addr.s_addr = htonl(INADDR_ANY);

    //socket bind:/
    if (bind(sockfd, (struct sockaddr *)&serv_addr, sizeof(serv_addr)) != 0)
    {
        fprintf(stderr, "socket bind failed\n");
        return -1;
    }

    /* Listen */
    if (listen(sockfd, 5) != 0)
    {
        fprintf(stderr, "listen failed\n");
        return -1;
    }

    addr_len = sizeof(cli_addr);
    connfd = accept(sockfd, (struct sockaddr *)&cli_addr, &addr_len);
    if (connfd < 0)
    {
        fprintf(stderr, "accept failed\n");
        return -1;
    }

    read(connfd, buffer, sizeof(buffer));
    printf("Message from client: %s\n", buffer);

    close(sockfd);
    return 0;
}
