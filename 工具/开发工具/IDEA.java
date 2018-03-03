1. -----------自动代码-------- 
2. -----------查询快捷键-------- 
3.---------------------其他快捷键-------------------
4.--------------svn快捷键---------------
5.--------------调试快捷键---------------
6.--------------重构--------------- 
7.----------------快速定位----------------


1. -----------自动代码-------- 

常用的有fori/sout/psvm+Tab即可生成循环、System.out、main方法等boilerplate样板代码 
例如要输入for(User user : users)只需输入user.for+Tab 

再比如，要输入Date birthday = user.getBirthday();只需输入user.getBirthday().var+Tab即可。代码标签输入完成后，按Tab，生成代码。

Ctrl+Alt+O 优化导入的类和包 
Alt+Insert 生成代码(如get,set方法,构造函数等)   或者右键（Generate） 
fori/sout/psvm + Tab  
Ctrl+Alt+T  生成try catch  或者 Alt+enter 
CTRL+ALT+T  把选中的代码放在 TRY{} IF{} ELSE{} 里 
Ctrl + O 重写方法  
Ctrl + I 实现方法 
Ctr+shift+U 大小写转化  
ALT+回车    导入包,自动修正  
ALT+/       代码提示 
CTRL+J      自动代码  
Ctrl+Shift+J，整合两行为一行 
CTRL+空格   代码提示  
CTRL+SHIFT+SPACE 自动补全代码  
CTRL+ALT+L  格式化代码  
CTRL+ALT+I  自动缩进  
CTRL+ALT+O  优化导入的类和包  
ALT+INSERT  生成代码(如GET,SET方法,构造函数等)  
CTRL+E      最近更改的代码  
CTRL+ALT+SPACE  类名或接口名提示  
CTRL+P   方法参数提示  
CTRL+Q，可以看到当前方法的声明 
  
Shift+F6  重构-重命名 (包、类、方法、变量、甚至注释等) 
Ctrl+Alt+V 提取变量 

2. -----------查询快捷键-------- 
CTRL+SHIFT+A 全局查询，包括功能
Ctrl＋Shift＋Backspace可以跳转到上次编辑的地 
CTRL+ALT+ left/right 前后导航编辑过的地方 
ALT+7  靠左窗口显示当前文件的结构 
Ctrl+F12 浮动显示当前文件的结构 
ALT+F7 找到你的函数或者变量或者类的所有引用到的地方 
CTRL+ALT+F7  找到你的函数或者变量或者类的所有引用到的地方 

Ctrl+Shift+Alt+N 查找类中的方法或变量 
双击SHIFT 在项目的所有目录查找文件 
Ctrl+N   查找类 
Ctrl+Shift+N 查找文件 
CTRL+G   定位行  
CTRL+F   在当前窗口查找文本  
CTRL+SHIFT+F  在指定窗口查找文本  
CTRL+R   在 当前窗口替换文本  
CTRL+SHIFT+R  在指定窗口替换文本  
ALT+SHIFT+C  查找修改的文件  
CTRL+E   最近打开的文件  
F3   向下查找关键字出现位置  
SHIFT+F3  向上一个关键字出现位置  
选中文本，按Alt+F3 ，高亮相同文本，F3逐个往下查找相同文本 
F4   查找变量来源  


CTRL+SHIFT+O  弹出显示查找内容 


Ctrl+W 选中代码，连续按会有其他效果 
F2 或Shift+F2 高亮错误或警告快速定位 
Ctrl+Up/Down 光标跳转到第一行或最后一行下 

Ctrl+B 快速打开光标处的类或方法  
CTRL+ALT+B  找所有的子类  
CTRL+SHIFT+B  找变量的类  


Ctrl+Shift+上下键  上下移动代码 
Ctrl+Alt+ left/right 返回至上次浏览的位置 
Ctrl+X 删除行 
Ctrl+D 复制行 
Ctrl+/ 或 Ctrl+Shift+/  注释（// 或者/*...*/ ） 


Ctrl+H 显示类结构图 
Ctrl+Q 显示注释文档 


Alt+F1 查找代码所在位置 
Alt+1 快速打开或隐藏工程面板 

Alt+ left/right 切换代码视图 
ALT+ ↑/↓  在方法间快速移动定位  
CTRL+ALT+ left/right 前后导航编辑过的地方 
Ctrl＋Shift＋Backspace可以跳转到上次编辑的地 
Alt+6    查找TODO 

3.---------------------其他快捷键------------------- 
SHIFT+ENTER 另起一行 
CTRL+Z   倒退(撤销) 
CTRL+SHIFT+Z  向前(取消撤销) 
CTRL+ALT+F12  资源管理器打开文件夹  
ALT+F1   查找文件所在目录位置  
SHIFT+ALT+INSERT 竖编辑模式  
CTRL+F4  关闭当前窗口 
Ctrl+Alt+V，可以引入变量。例如：new String(); 自动导入变量定义 
Ctrl+~，快速切换方案（界面外观、代码风格、快捷键映射等菜单） 

4.--------------svn快捷键--------------- 

ctrl+k 提交代码到SVN 
ctrl+t 更新代码 


5.--------------调试快捷键---------------

其实常用的 就是F8 F7 F9 最值得一提的 就是Drop Frame  可以让运行过的代码从头再来

alt+F8          debug时选中查看值 
Alt+Shift+F9，选择 Debug 
Alt+Shift+F10，选择 Run 
Ctrl+Shift+F9，编译 
Ctrl+Shift+F8，查看断点 

F7，步入 
Shift+F7，智能步入 
Alt+Shift+F7，强制步入 
F8，步过 
Shift+F8，步出 
Alt+Shift+F8，强制步过 

Alt+F9，运行至光标处 
Ctrl+Alt+F9，强制运行至光标处 
F9，恢复程序 
Alt+F10，定位到断点 

6.--------------重构--------------- 
Ctrl+Alt+Shift+T，弹出重构菜单 
Shift+F6，重命名 
F6，移动 
F5，复制 
Alt+Delete，安全删除 
Ctrl+Alt+N，内联 




7.----------------快速定位----------------
F11 为代码行加上书签
CTRL + F11 为书签加上一个标记 如：1
SHIFT + F11 显示书签列表
CTRL + 书签标记 快速定位到该书签位置
CTRL + N 查找文件，相当于eclipse的 Ctrl+Shift+R
CTRL + ALT + SHIFT + N: 全局搜索方法名
CTRL + SHIFT + F:全局搜索字符串



8.----------------代码小助手----------------
8.1：批量列操作
CTRL + -> 选择整个单词 
CTRL + SHIFT + U  大小写转化
CTRL + ALT + SHIFT + J  选择当前文件所有相同字符的地方

8.2：
CTRL + ALT + L 格式化代码

8.3：
代码提示
ALT + ENTER 智能创建所需代码
(1)单词拼写
(2)实现接口
(3)导包
(4)自动创建函数
(5)list replace
(6)字符串formate或者build




9.----------------编写高质量代码----------------
9.1 重构
重构变量 和 方法
Shift + F6 重命名变量
CTRL + F6 重命名方法

9.2 抽取
抽取变量