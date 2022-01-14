# Git操作

## 将本地代码与远程仓库绑定

> 适用场景：有了本地代码，想要将本地代码放到远程仓库保存，同时能够保证自己更改本地代码后可以更新到远程仓库，此时可以将代码放到GitHub仓库，具体步骤如下。

1. 初始化本地仓库 

   ```bash
   git init
   ```

   

2. 建立对应的远程仓库

   ![image-20220110195403104](https://masuo-github-image.oss-cn-beijing.aliyuncs.com/image/image-20220110195403104.png)

3. 关联本地仓库与远程仓库

   ```bash
   # url看下图
   git remote add origin <远程仓库url>
   ```

   ![image-20220110200941538](https://masuo-github-image.oss-cn-beijing.aliyuncs.com/image/image-20220110200941538.png)

4. 如果远程仓库与本地仓库文件不一致（含有初始化文件或其他提交分支导致）时，需要先拉取当前分支

   ```bash
   # origin 是远程仓库的意思 
   # main 是远程仓库的默认分支（看你自己的，上图也可以看到）
   git pull origin main
   ```

   

5. 将代码添加到本地仓库

   ```bash
   git add .
   ```

   

6. 将代码提交到本地仓库，并可以添加提交备注

   ```bash
   git commit -m "提交备注"
   ```

   

7. 将本地代码推到远程仓库

   ```bash
   git push origin main
   ```

   


## 将本地代码提交到远程仓库步骤

1. 将待更新文件添加到本地仓库

    ```bash
    git add .
    ```

    

2. 提交本地仓库（携带注释信息）

    ```bash
    # m message 提交信息
    git commit -m "注释信息"
    ```

    

3. 拉取所在分支的远程仓库

    ```bash
    git pull origin <分支名>
    ```

    

4. 将本地仓库推到远程仓库

    ```bash
    git push origin <分支名>
    ```

    
    
    

# target

```markdown
框架，中间件，数据库，缓存，微服务，分布式这些原理都要有了解，可以说的不完整，但是不能不知道
常规的技术必须要储备扎实，不单单是应用，原理要了解，项目的话，准备一个微服务的，一个普通的，基本够用了，
操作系统，网络，机组，这部分属于基础素质，一般面试问不到，高级的才会问道，有时间最好能过一下，至于高薪，就是会点别人不会的东西，原理源码之类的，你觉得难的东西一定要学，你觉得难别人也觉得难
```

