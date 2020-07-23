alert("test");
var i;
i = 12;
alert(i);
alert(typeof(i))
var b = "abc";
alert(a * b);
a = "12";
b = 12;
alert(a == b);//true
alert(a === b);//false

var arr = [];//定义一个空数组
alert(arr.length);
arr[0] = 12;
alert(arr[0]);
alert(arr.length);
arr[2] = "abc";
alert(a.length);
for(var i = 0; i < arr.length; i++){
    alert(arr[i]);
}

function func(){
    alert("function");
}

func();

function func2(a, b){//var a是错的
    alert("paremeter" + a + "=" + b);
}

func2(12,"abc");


function func3(num1, num2){
    var result = num1, num2;
    return result;
}

alert(func3(100,20));


var f1 = function(){
    alert("v2 func");
}

var f2 = function(a,b){
    return a + b;
}

alert(f2(1,2));

function func5(){
    alert(arguments.length);
    for(var i = 0; i <= argments.length; i++){
        alert(arguments[i]);
    }

}


var obj = new Object();
alert(typeof (obj));

obj.name = "pony";
obj.age = 18;
obj.func1 = function(){
    alert("name" + this.name + "age" + this.age);
}

obj.func1();


func5(6,7,5);



var obj1 = {
    name:"tony",//注意是逗号
    age:18,
    fun1:function(){
        alert("name" + this.name);
    }

};



//第二种定义

/*
js数据类型：

数值类型：number
字符串类型：string
对象类型：object
布尔类型：boolean
函数类型：function

三个特殊的值：
    undefined：未定义，所有js变量未赋初始值的时候，默认值都是undefined
    null：空值
    NAN：全称：Not a Number：非数字，非数值

定义变量格式：
    var 变量名;

关系比较运算：
    等于：== 简单做字面值比较
    全等于：=== 字面值和类型都要相等

逻辑运算
    且：&&
        当表达式全为真的时候，返回最后一个表达式的值
        当表达式有一个为假时，返回第一个为假的值
    或：||
        当表达式全为假的时候，返回最后一个表达式的值
        当表达式有一个为真时，返回第一个为假的值
    取反：！

    再java中，所有变量都可以当成boolean变量
    0，null,undefined,""（空字符串）都是false

js中对数组下标赋值，那么会自动扩容


函数定义：
    function 函数名(形参列表){
        函数体
    }

    或者：

    var 函数名 = function(形参列表){
        函数体；
    }


js中不允许函数重载，只会重写覆盖



隐形参数：在function内不需要定义，但却可以直接用来获取所有参数的变量，我们称其为
    隐形参数。本体是一个名为argments的数组


自定义对象：
    var 变量名 = new Object();//对象实例
    变量名.属性名 = 值 //定义一个属性
    变量名.函数名 = function(){} //定义一个函数

访问对象：
    变量名.属性名/函数名


大括号形式自定义对象：

var obj = {
    属性名：值，属性名2：值2...,函数名：function（）{}



}；

 */