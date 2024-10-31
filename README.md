# Zero-FastDFS
Zero-FastDFS
# 1. 概述
## 1.1.简介
FastDFS 是一个开源的高性能分布式文件系统（DFS）。 它的主要功能包括：文件存储，文件同步和文件访问，以及高容量和负载平衡。
主要解决了海量数据存储问题，特别适合以中小文件（建议范围：4KB < file_size <500MB）为载体的在线服务。
FastDFS 系统有三个角色：跟踪服务器(Tracker Server)、存储服务器(Storage Server)和客户端(Client)。
1. Tracker Server：跟踪服务器，主要做调度工作，起到均衡的作用；负责管理所有的 storage server和 group，每个 storage 在启动后会连接 Tracker，告知自己所属 group 等信息，并保持周期性心跳。
2. Storage Server：存储服务器，主要提供容量和备份服务；以 group 为单位，每个 group 内可以有多台 storage server，数据互为备份。
3. Client：客户端，上传下载数据的服务器，也就是我们自己的项目所部署在的服务器。

# 2. 功能

# 3. 使用
## 3.1. 安装
### 3.1.1.搜索镜像
```
docker search fastdfs
```

### 3.1.2.拉取镜像（已经内置Nginx）
```
docker pull delron/fastdfs
```

### 3.1.3.构建Tracker
```
# 22122 => Tracker默认端口
docker run --name=tracker-server --privileged=true -p 22122:22122 -v /var/fdfs/tracker:/var/fdfs  --network=host -d delron/fastdfs tracker
```
### 3.1.4.构建Storage
```
# 23000 => Storage默认端口
# 8888 => 内置Nginx默认端口
# TRACKER_SERVER => 执行Tracker的ip和端口
# --net=host => 避免因为Docker网络问题导致外网客户端无法上传文件，因此使用host网络模式
docker run --name=storage-server --privileged=true -p 23000:23000 -p 8888:8888 -v /var/fdfs/storage:/var/fdfs -e TRACKER_SERVER=10.11.68.77:22122 -e GROUP_NAME=group1  --network=host -d delron/fastdfs storage
```
### 3.1.5.查看容器
```
docker ps
```

# 4. 其他

# 5. 参考