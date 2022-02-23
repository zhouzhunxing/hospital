#!/bin/bash
# 这是一个注释11111111111111111111111111111111111111111
#下边是一个多行注释
:<<EOF
echo 'hello world!1'nnnnnnnnnnnnnnnnnnnnnnnnnnnnnn
echo 'hello world!2'
echo 'hello world!3'
EOF
#echo 'hello world!你好'

#count=1
#name='zzx'
#echo $count
#echo $name

#DATE1=`date`
#DATE2=$(date)
#echo $DATE1
#echo $DATE2

#url="http://www.baidu.com"
#readonly url #设置变量为只读模式

#name="zzx"
##echo "我的名字是: $name"
#
#num1=100
#num2=100
#if test $num1 -eq $num2
#then
#    echo '两个数相等！'
#else
#    echo '两个数不相等！'
#fi

#echo '输入 1 到 4 之间的数字:'
#echo '你输入的数字为:'
#read num
#case $num in
#    1)  echo '你选择了 1'
#    ;;
#    2)  echo '你选择了 2'
#    ;;
#    3)  echo '你选择了 3'
#    ;;
#    4)  echo '你选择了 4'
#    ;;
#    *)  echo '你没有输入 1 到 4 之间的数字'
#    ;;
#esac

#while :
#do
#    echo -n "输入 1 到 5 之间的数字:"
#    read num
#    case $num in
#        1|2|3|4|5) echo "你输入的数字为 $num!"
#        ;;
#        *) echo "你输入的数字不是 1 到 5 之间的! 游戏结束"
#            break
#        ;;
#    esac
#done

#for num in 1 2 3 4 5
#do
#    echo "The value is: $num"
#done

#num=1
#while(( $num<=5 ))
#do
#    echo $num
#    let "num++"
#done

#a=0
#until [ ! $a -lt 5 ]
#do
#   echo $a
#   a=`expr $a + 1`
#done


#FunReturn(){
#    echo "两个数字进行相加运算..."
#    echo "输入第一个数字: "
#    read num
#    echo "输入第二个数字: "
#    read anothernum
#    echo "两个数字分别为 $num 和 $anothernum !"
#    return $(($num+$anothernum))	# 分别返回数值
#}
#FunReturn		# 调用函数
#echo "输入的两个数字之和为 $? !" # 使用通配符获取上一条指令的返回值

FunParam(){
    echo "输入第一个参数 $1 !"
    echo "输入第二个参数 $2 !"
    echo "输入第十个个参数 $10 !"
    echo "参数总数共 $# 个!"
    echo "作为一个字符串输出所有参数 $* !"
}
FunParam 1 2 3 4 5 6 7 8 9 10
