# 第九周（第六章）作业
### 第一题  略
### 第二题  略
### 第三题  略

* * *
#### 在开始第四题之前：
先来将所谓的“行优先”以及“列优先”的存储方式捋一捋
  
##### 以“行优先”为例
**行优先**事实上应该称为**左下标优先**，因为在数组的维度大于二维之后“行”和“列”的概念根本就已经变得意义不明了

**低维（二维数组）**
在对低维（也就是二维）数组进行存储的时候，我们有如下规律：
首先在此假设数组的形式为
  
![equation](http://latex.codecogs.com/gif.latex?%24%24a%5Bi%5D%5Bj%5D%20%2Ci%20%5Cin%20%5B1%2C2%2C3%20%5Ccdots%20m%5D%2Cj%20%5Cin%20%5B1%20%2C2%2C3%5Ccdots%20n%5D%24%24)
  
多维数组的递归定义如下：
（1）一维数组是一个向量
（2）n维数组是个向量，每个元素是一个n-1维的数组
由于数组在内存中的存储完全是线性的，在行优先的情况下，二维数组的存储方式是将每一行的元素都存完之后再去存下一行,顺序如下：
![2D.jpeg](https://github.com/RayChromium/lzu-Datastructure-assignments/blob/master/Chapter_6/images/low01.jpg)
  
在左下标优先的情况下，如果按照存储的顺序去依次访问数组元素的话会是这么个顺序：
  
![equation](http://latex.codecogs.com/gif.latex?%24%24a%5B1%5D%5B1%5D%2Ca%5B1%5D%5B2%5D%20%5Ccdots%20a%5B1%5D%5Bn%5D%24%24)
  
![equation](http://latex.codecogs.com/gif.latex?%24%24a%5B2%5D%5B1%5D%2Ca%5B2%5D%5B2%5D%5Ccdots%20a%5B2%5D%5Bn%5D%24%24)
  
可见左下标的变化速度要远远小于右下标

**推广到高维（这里仅以三维为例，方便理解）**
设有数组
![equation](http://latex.codecogs.com/gif.latex?%24%24A%28c_1..d_1%2Cc_2..d_2%2C%5Ccdots%2Cc_n..d_n%29%24%24)
  
首地址为
![equation](http://latex.codecogs.com/gif.latex?%24%24Loc%28c_1%2Cc_2%2C%5Ccdots%2Cc_n%29%20%3D%20AO%28A%29%24%24)
  
每个元素长度为![equation](http://latex.codecogs.com/gif.latex?%24l%24),那么任一数据元素![equation](http://latex.codecogs.com/gif.latex?%24A%28j_1%2Cj_2%2C%5Ccdots%2Cj_n%29%24)的地址公式为：
  
![equation](http://latex.codecogs.com/gif.latex?Loc%28j_1%2Cj_2%2C%5Ccdots%2Cj_n%29%20%3D%20AO%28A%29&plus;l%5Ccdot%5B%28j_1-c_1%29%5Ccdot%28d_2-c_2&plus;1%29%5Ccdot%28d_3-c_3&plus;1%29%5Ccdots%28d_n-c_n&plus;1%29&plus;%28j_2-c_2%29%5Ccdot%28d_3-c_3&plus;1%29%5Ccdots%28d_n-c_n&plus;1%29&plus;%5Ccdots&plus;%28j_%7Bn-1%7D-c_%7Bn-1%7D%29%5Ccdot%28d_n-c_n%29&plus;%28j_n-c_n%29%5D%5C%5C%3DAO%28A%29&plus;l%5Ccdot%5B%5Csum_%7Bi%3D1%7D%5E%7Bn-1%7D%28j_i-c_i%29%5Cprod_%7Bk%3Di&plus;1%7D%5E%7Bn%7D%28d_k-c_k&plus;1%29&plus;%28j_n-c_n%29%5D)
  
好了，相信上课讲到这的时候不少人已经像我一样开始有点懵了，为了方便理解，也许有必要将这个公式转化成某种图像来理解一下

现将这个公式套用到一个三维数组的存储中去

假设现有一个![equation](http://latex.codecogs.com/gif.latex?%242%5Ctimes3%5Ctimes4%24)的三维数组
![equation](http://latex.codecogs.com/gif.latex?%24%24A%28i%2Cj%2Ck%29%2Ci%5Cin%5B1%2C2%5D%2Cj%5Cin%5B1%2C2%2C3%5D%2Ck%5Cin%5B1%2C2%2C3%2C4%5D%24%24)
那么按照左下标优先的存储方式，它在内存中存储的顺序应该是这样的：![3D_01.png](https://github.com/RayChromium/lzu-Datastructure-assignments/blob/master/Chapter_6/images/3D_01.png)
![3D_02.png](https://github.com/RayChromium/lzu-Datastructure-assignments/blob/master/Chapter_6/images/3D_02.png)
由于是左下标优先，在存储的过程中数组下表的变化频率是从右往左逐次递减的

那么，如果我们要求出这个三维数组中元素![equation](http://latex.codecogs.com/gif.latex?%24A%282%2C3%2C3%29%24)的地址![equation](http://latex.codecogs.com/gif.latex?%24Loc%282%2C3%2C3%29%24),代入公式得到
![equation](http://latex.codecogs.com/gif.latex?%24%24Loc%282%2C3%2C3%29%3DAO%28A%29&plus;l%5Ccdot%5B%5Cunderbrace%7B%282-1%29%5Ccdot%283-1&plus;1%29%5Ccdot%284-1&plus;1%29%7D_%7Bitem1%7D&plus;%5Cunderbrace%7B%283-1%29%5Ccdot%284-1&plus;1%29%7D_%7Bitem2%7D&plus;%5Cunderbrace%7B%283-1%29%7D_%7Bitem3%7D%5D%5C%5C%3D%20AO%28A%29&plus;l%5Ccdot%28%5Cunderbrace%7B1%5Ccdot3%5Ccdot4%7D_%7Bitem1%7D&plus;%5Cunderbrace%7B2%5Ccdot4%7D_%7Bitem2%7D&plus;%5Cunderbrace%7B2%7D_%7Bitem3%7D%29%24%24)
公式主要由数组的"起始地址![equation](http://latex.codecogs.com/gif.latex?%24AO%28A%29%24)+偏移量"组成，偏移量就是括号中的三项。这三项的具体几何含义如下：

- 第一项(item 1)
![3D_03.png](https://github.com/RayChromium/lzu-Datastructure-assignments/blob/master/Chapter_6/images/3D_03.png)
- 第二项(item 2)
![3D_04.png](https://github.com/RayChromium/lzu-Datastructure-assignments/blob/master/Chapter_6/images/3D_04.png)
- 第三项(item 3)
![3D_05.png](https://github.com/RayChromium/lzu-Datastructure-assignments/blob/master/Chapter_6/images/3D_05.png)

至少这样能够在三维以下的空间中解释这个地址计算公式了

* * *
### 第四题
将上述的地址公式变化一下就能得到
  
![equation](http://latex.codecogs.com/gif.latex?%24%24Loc%28j_1%2Cj_2%2C%5Ccdots%2Cj_n%29%3DVO%28A%29%24%24)
  
虚拟地址
  
![equation](http://latex.codecogs.com/gif.latex?%24%24VO%28A%29%3DAO%28A%29-%5Csum_%7Bi%3D1%7D%5E%7Bn%7Da_ic_i%24%24)
  
其中
  
![equation](http://latex.codecogs.com/gif.latex?%24%24%5Clbrace%5E%7Ba_i%3Dl%5Ccdot%5Cprod_%7Bk%3Di&plus;1%7D%5E%7Bn%7D%28d_k-c_k&plus;1%29%2C1%5Cleq%20i%5Cleq%20n-1%20%7D_%7Ba_i%3Dl%2Ci%3Dn%7D%24%24)
  
这也太复杂了，![equation](http://latex.codecogs.com/gif.latex?%24a_i%24)的递归计算方式如下
  
![equation](http://latex.codecogs.com/gif.latex?%24%24%5Clbrace%5E%7Ba_%7Bi-1%7D%3Dd_i-c_i&plus;1%20%2C1%3C%20i%5Cleq%20n-1%20%7D_%7Ba_i%3Dl%2Ci%3Dn%7D%24%24)
  
在这题里
  
![equation](http://latex.codecogs.com/gif.latex?%24%24a_4%3Dl%3D4%5C%5Ca_3%20%3D%20a_4%28d_4-c_4&plus;1%29%20%3D%204%5Ctimes%287-0&plus;1%29%3D32%5C%5Ca_2%20%3D%20a_3%28d_3-c_3&plus;1%29%3D32%5Ctimes%280-%28-4%29&plus;1%29%3D160%5C%5Ca_1%3Da_2%28d_2-c_2&plus;1%29%3D160%5Ctimes%285-3&plus;1%29%3D160%5Ctimes3%3D480%5C%5CVO%28A%29%3D100-%28480%5Ctimes%28-3%29&plus;160%5Ctimes3&plus;32%5Ctimes%28-4%29&plus;4%5Ctimes0%29%3D1188%24%24)
  
元素![equation](http://latex.codecogs.com/gif.latex?A%280%2C4%2C-2%2C5%29)的地址：
  
![equation](http://latex.codecogs.com/gif.latex?Loc%280%2C4%2C-1%2C5%29%3D1188&plus;480%5Ctimes0&plus;160%5Ctimes4&plus;32%5Ctimes%28-2%29&plus;4%5Ctimes5%3D1784)
  
### 第五题
为了节省空间，一般只存储三角矩阵的非零部分。这里认为是以行优先（左下标优先）的方式存储了上三角矩阵U
  
![equation](http://latex.codecogs.com/gif.latex?%24%24U%3D%20%5Cleft%5B%20%5Cbegin%7Bmatrix%7D%20a_%7B11%7D%20%26%20a_%7B12%7D%20%26%20a_%7B13%7D%20%26%20%5Ccdots%20%26%20a_%7B1%20%2Cn-1%7D%20%26%20a_%7B1%2Cn%7D%5C%5C%200%20%26%20a_%7B22%7D%20%26%20a_%7B23%7D%20%26%20%5Ccdots%20%26%20a_%7B2%2Cn-1%7D%20%26a_%7B2%2Cn%7D%5C%5C%200%20%26%200%20%26%20a_%7B33%7D%20%26%20%5Ccdots%20%26%20a_%7B3%2Cn-1%7D%20%26%20a_%7B3%2Cn%7D%5C%5C%20%5Cvdots%20%26%200%20%26%200%20%26%20%5Cddots%5C%5C%200%20%26%20%5Ccdots%20%26%20%5Ccdots%20%26%20%5Ccdots%20%26%20a_%7Bn-1%2Cn-1%7D%20%26%20a_%7Bn-1%2Cn%7D%5C%5C%200%20%26%200%20%26%20%5Ccdots%20%26%20%5Ccdots%20%260%20%26a_%7Bn%2Cn%7D%20%5Cend%7Bmatrix%7D%20%5Cright%5D%20%24%24)
  
设![equation](http://latex.codecogs.com/gif.latex?%24Loc%28i%2Cj%29%24)为元素![equation](http://latex.codecogs.com/gif.latex?%24U%28i%2Cj%29%24)的地址，那么有：
  
第1行到第i-1行的元素总数为：
  
![equation](http://latex.codecogs.com/gif.latex?%24%24n&plus;%28n-1%29&plus;%28n-2%29&plus;%5Ccdots&plus;%28i-2%29&plus;%28i-1%29%3D%5Cfrac%7B%28n&plus;i-1%29%28n-i&plus;2%29%7D%7B2%7D%24%24)
  
第i行在U(i,j)之前的元素个数：
  
![equation](http://latex.codecogs.com/gif.latex?%24%24j-i%24%24)
若一个元素在存储空间中的长度为l，则
  
![equation](http://latex.codecogs.com/gif.latex?%24%24Loc%28i%2Cj%3DAO%28A%29&plus;%5B%5Cfrac%7B%28n&plus;i-1%29%28n-i&plus;2%29%7D%7B2%7D&plus;j-i%5D%24%24)
### 第六题  略
### 第七题  略
