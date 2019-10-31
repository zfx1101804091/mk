package com.example.mk.controller;

import com.alibaba.fastjson.JSON;
import com.example.mk.bean.OneS;
import com.example.mk.mapper.OneSMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("test")
public class OnesController {

    @Autowired
    private OneSMapper oneSMapper;

    @RequestMapping("hello")
    @ResponseBody
    public String test(){

        return "helloword";
    }

    /*
     * 功能描述: <br>
     * 〈查询所有one_s表中的对象〉
     * @Param: [request]
     * @Return: java.lang.String
     * @Author: Administrator
     * @Date: 2019/9/17 0017 21:49
     *///http://localhost:8080/test/query_all
    @RequestMapping("/query_all")
    @ResponseBody
    //获取他的请求的文件  oneSMapper为方法的接口
    public String query_all(HttpServletRequest request) throws Exception{
        
        List<OneS> oneS = oneSMapper.selectByExample(null);

        System.out.println("查询所有one_s对象----"+JSON.toJSONString(oneS));
        return JSON.toJSONString(oneS);
    }
    /*
     * 功能描述: <br>
     * 〈通过id查询某个one_s对象〉
     * @Param: []
     * @Return: java.lang.String
     * @Author: Administrator
     * @Date: 2019/9/17 0017 21:56
     */
    @RequestMapping("/query_one_byId")
    @ResponseBody
    public String query_one_byId(){

        OneS oneS = oneSMapper.selectByPrimaryKey(1);

        System.out.println("通过id查询----"+JSON.toJSONString(oneS));
        return JSON.toJSONString(oneS);
    }

    /*
     * 功能描述: <br>
     * 〈通过id删除某个one_s对象〉
     * @Param: []
     * @Return: java.lang.String
     * @Author: Administrator
     * @Date: 2019/9/17 0017 21:57
     */
    @RequestMapping("/deleteById")
    @ResponseBody
    public String deleteById(){

        int i = oneSMapper.deleteByPrimaryKey(7);
        if(i==1){
            System.out.println("删除id为"+i+"的one_s表数据");
        }
        return "删除成功";
    }

    /*
     * 功能描述: <br>
     * 〈新增〉
     * @Param: []
     * @Return: java.lang.String
     * @Author: Administrator
     * @Date: 2019/9/17 0017 22:00
     */
    @RequestMapping("/insert")
    @ResponseBody
    public String insert(){
        OneS oneS = new OneS();
        oneS.setNameS("大保健服务2");
        oneS.setValueS(1200);
        try {
            int insert = oneSMapper.insert(oneS);
        } catch (Exception e) {
            e.printStackTrace();
            return "新增失败";
        }
        return "新增成功"+JSON.toJSONString(oneS);
    }

    @RequestMapping("/updateone")
    @ResponseBody
    public String updateone(){

        OneS oneS = oneSMapper.selectByPrimaryKey(8);
        oneS.setNameS("特殊服务");
        int i = oneSMapper.updateByPrimaryKey(oneS);
        if(i==1){
            System.out.println("更新一条one_s数据"+JSON.toJSONString(oneS));
        }
        return "更新成功"+JSON.toJSONString(oneS);
        
    }
}













































   /*  js。js
    $(function () {
        echarts_1();
        echarts_2();
        echarts_4();
        echarts_31();
        echarts_32();
        echarts_33();
        echarts_5();
        echarts_6();
        function echarts_1() {
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('echart1'));

            option = {
                    //  backgroundColor: '#00265f',
                    tooltip: {
                trigger: 'axis',
                        axisPointer: {
                    type: 'shadow'
                }
            },
            grid: {
                left: '0%',
                        top:'10px',
                        right: '0%',
                        bottom: '4%',
                        containLabel: true
            },
            //todo -------------------
            xAxis: [{
                type: 'category',
                        data: ['商超门店', '教育培训', '房地产', '生活服务', '汽车销售', '旅游酒店', '五金建材'],//教育培训房地产生活服务名字
                axisLine: {
                    show: true,
                            lineStyle: {
                        color: "rgba(255,255,255,.1)",
                                width: 1,
                                type: "solid"
                    },
                },

                axisTick: {
                    show: false,
                },
                axisLabel:  {
                    interval: 0,
                            // rotate:50,
                            show: true,
                            splitNumber: 15,
                            textStyle: {
                        color: "rgba(255,255,255,.6)",
                                fontSize: '12',
                    },
                },
            }],
            yAxis: [{
                type: 'value',
                        axisLabel: {
                    //formatter: '{value} %'
                    show:true,
                            textStyle: {
                        color: "rgba(255,255,255,.6)",
                                fontSize: '12',
                    },
                },
                axisTick: {
                    show: false,
                },
                axisLine: {
                    show: true,
                            lineStyle: {
                        color: "rgba(255,255,255,.1	)",
                                width: 1,
                                type: "solid"
                    },
                },
                splitLine: {
                    lineStyle: {
                        color: "rgba(255,255,255,.1)",
                    }
                }
            }],
            series: [
            {
                type: 'bar',
                        data: [200, 300, 300, 900, 1500, 1200, 600],//教育培训房地产生活服务数据
                barWidth:'35%', //柱子宽度
                        // barGap: 1, //柱子之间间距
                        itemStyle: {
                normal: {
                    color:'#2f89cf',
                            opacity: 1,
                            barBorderRadius: 5,
                }
            }
            }

	]
};

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
            window.addEventListener("resize",function(){
                myChart.resize();
            });
        }
        function echarts_2() {
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('echart2'));

            option = {
                    //  backgroundColor: '#00265f',
                    tooltip: {
                trigger: 'axis',
                        axisPointer: { type: 'shadow'}
            },
            grid: {
                left: '0%',
                        top:'10px',
                        right: '0%',
                        bottom: '4%',
                        containLabel: true
            },
            xAxis: [{
                type: 'category',
                        data: ['浙江', '上海', '江苏', '广东', '北京', '深圳', '安徽'],//教育培训房地产生活服务名字
                axisLine: {
                    show: true,
                            lineStyle: {
                        color: "rgba(255,255,255,.1)",
                                width: 1,
                                type: "solid"
                    },
                },

                axisTick: {
                    show: false,
                },
                axisLabel:  {
                    interval: 0,
                            // rotate:50,
                            show: true,
                            splitNumber: 15,
                            textStyle: {
                        color: "rgba(255,255,255,.6)",
                                fontSize: '12',
                    },
                },
            }],
            yAxis: [{
                type: 'value',
                        axisLabel: {
                    //formatter: '{value} %'
                    show:true,
                            textStyle: {
                        color: "rgba(255,255,255,.6)",
                                fontSize: '12',
                    },
                },
                axisTick: {
                    show: false,
                },
                axisLine: {
                    show: true,
                            lineStyle: {
                        color: "rgba(255,255,255,.1	)",
                                width: 1,
                                type: "solid"
                    },
                },
                splitLine: {
                    lineStyle: {
                        color: "rgba(255,255,255,.1)",
                    }
                }
            }],
            series: [
            {

                type: 'bar',
                        data: [1500, 1200, 600, 200, 300, 300, 900],//教育培训房地产生活服务数据
                barWidth:'35%', //柱子宽度
                        // barGap: 1, //柱子之间间距
                        itemStyle: {
                normal: {
                    color:'#27d08a',
                            opacity: 1,
                            barBorderRadius: 5,
                }
            }
            }

	]
};

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
            window.addEventListener("resize",function(){
                myChart.resize();
            });
        }
        function echarts_5() {
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('echart5'));

            option = {
                    //  backgroundColor: '#00265f',
                    tooltip: {
                trigger: 'axis',
                        axisPointer: {
                    type: 'shadow'
                }
            },

            grid: {
                left: '0%',
                        top:'10px',
                        right: '0%',
                        bottom: '2%',
                        containLabel: true
            },
            xAxis: [{
                type: 'category',
                        data: ['浙江', '上海', '江苏', '广东', '北京', '深圳', '安徽', '四川'],//教育培训房地产生活服务名字
                axisLine: {
                    show: true,
                            lineStyle: {
                        color: "rgba(255,255,255,.1)",
                                width: 1,
                                type: "solid"
                    },
                },

                axisTick: {
                    show: false,
                },
                axisLabel:  {
                    interval: 0,
                            // rotate:50,
                            show: true,
                            splitNumber: 15,
                            textStyle: {
                        color: "rgba(255,255,255,.6)",
                                fontSize: '12',
                    },
                },
            }],
            yAxis: [{
                type: 'value',
                        axisLabel: {
                    //formatter: '{value} %'
                    show:true,
                            textStyle: {
                        color: "rgba(255,255,255,.6)",
                                fontSize: '12',
                    },
                },
                axisTick: {
                    show: false,
                },
                axisLine: {
                    show: true,
                            lineStyle: {
                        color: "rgba(255,255,255,.1	)",
                                width: 1,
                                type: "solid"
                    },
                },
                splitLine: {
                    lineStyle: {
                        color: "rgba(255,255,255,.1)",
                    }
                }
            }],
            series: [{
                type: 'bar',
                        data: [2, 3, 3, 9, 15, 12, 6, 4, 6, 7, 4, 10],//教育培训房地产生活服务参数
                barWidth:'35%', //柱子宽度
                        // barGap: 1, //柱子之间间距
                        itemStyle: {
                    normal: {
                        color:'#2f89cf',
                                opacity: 1,
                                barBorderRadius: 5,
                    }
                }
            }
	]
};

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
            window.addEventListener("resize",function(){
                myChart.resize();
            });
        }

        function echarts_4() {
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('echart4'));

            option = {
                    tooltip: {
                trigger: 'axis',
                        axisPointer: {
                    lineStyle: {
                        color: '#dddc6b'
                    }
                }
            },
            legend: {
                top:'0%',
                        data:['安卓','IOS'], //曲线数据
                textStyle: {
                    color: 'rgba(255,255,255,.5)',
                            fontSize:'12',
                }
            },
            grid: {
                left: '10',
                        top: '30',
                        right: '10',
                        bottom: '10',
                        containLabel: true
            },

            xAxis: [{
                type: 'category',
                        boundaryGap: false,
                        axisLabel:  {
                    textStyle: {
                        color: "rgba(255,255,255,.6)",
                                fontSize:12,
                    },
                },
                axisLine: {
                    lineStyle: {
                        color: 'rgba(255,255,255,.2)'
                    }

                },

                data: ['01', '02', '03', '04', '05', '06', '07', '08', '09', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24']

            }, {

                axisPointer: {show: false},
                axisLine: {  show: false},
                position: 'bottom',
                        offset: 20,



            }],

            yAxis: [{
                type: 'value',
                        axisTick: {show: false},
                axisLine: {
                    lineStyle: {
                        color: 'rgba(255,255,255,.1)'
                    }
                },
                axisLabel:  {
                    textStyle: {
                        color: "rgba(255,255,255,.6)",
                                fontSize:12,
                    },
                },

                splitLine: {
                    lineStyle: {
                        color: 'rgba(255,255,255,.1)'
                    }
                }
            }],
            series: [
            {
                name: '安卓',
                        type: 'line',
                    smooth: true,
                    symbol: 'circle',
                    symbolSize: 5,
                    showSymbol: false,
                    lineStyle: {

                normal: {
                    color: '#0184d5',
                            width: 2
                }
            },
                areaStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                offset: 0,
                                color: 'rgba(1, 132, 213, 0.4)'
                }, {
                            offset: 0.8,
                                    color: 'rgba(1, 132, 213, 0.1)'
                        }], false),
                        shadowColor: 'rgba(0, 0, 0, 0.1)',
                    }
                },
                itemStyle: {
                    normal: {
                        color: '#0184d5',
                                borderColor: 'rgba(221, 220, 107, .1)',
                                borderWidth: 12
                    }
                },
                //安卓波浪的数据
                data: [3, 4, 3, 4, 3, 4, 3, 6, 2, 4, 2, 4,3, 4, 3, 4, 3, 4, 3, 6, 2, 4, 2, 4]

            },
            {
                name: 'IOS',
                        type: 'line',
                    smooth: true,
                    symbol: 'circle',
                    symbolSize: 5,
                    showSymbol: false,
                    lineStyle: {

                normal: {
                    color: '#00d887',
                            width: 2
                }
            },
                areaStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                offset: 0,
                                color: 'rgba(0, 216, 135, 0.4)'
                }, {
                            offset: 0.8,
                                    color: 'rgba(0, 216, 135, 0.1)'
                        }], false),
                        shadowColor: 'rgba(0, 0, 0, 0.1)',
                    }
                },
                itemStyle: {
                    normal: {
                        color: '#00d887',
                                borderColor: 'rgba(221, 220, 107, .1)',
                                borderWidth: 12
                    }
                },
                //ios波浪的数据
                data: [5, 3, 5, 6, 1, 5, 3, 5, 6, 4, 6, 4, 8, 3, 5, 6, 1, 5, 3, 7, 2, 5, 1, 4]

            },

		 ]

};

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
            window.addEventListener("resize",function(){
                myChart.resize();
            });
        }
        function echarts_6() {
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('echart6'));

            var dataStyle = {
                    normal: {
                label: {
                    show: false
                },
                labelLine: {
                    show: false
                },
                //shadowBlur: 40,
                //shadowColor: 'rgba(40, 40, 40, 1)',
            }
};
            var placeHolderStyle = {
                    normal: {
                color: 'rgba(255,255,255,.05)',
                        label: {show: false,},
                labelLine: {show: false}
            },
            emphasis: {
                color: 'rgba(0,0,0,0)'
            }
};
            option = {
                    color: ['#0f63d6', '#0f78d6', '#0f8cd6', '#0fa0d6', '#0fb4d6'],
            tooltip: {
                show: true,
                        formatter: "{a} : {c} "
            },
            legend: {
                itemWidth: 10,
                        itemHeight: 10,
                        itemGap: 12,
                        bottom: '3%',
                        //图形展示
                        data: ['浙江', '上海', '广东', '北京', '深圳'],
                textStyle: {
                    color: 'rgba(255,255,255,.6)',
                }
            },
            //todo   浙江 ..等数据
            series: [
            {
                name: '浙江',
                        type: 'pie',
                    clockWise: false,
                    center: ['50%', '42%'],
                radius: ['59%', '70%'],
                itemStyle: dataStyle,
                        hoverAnimation: false,
                    data: [{
                value: 80,//数据
                        name: '01'
            }, {
                value: 20,
                        name: 'invisible',
                        tooltip: {show: false},
                itemStyle: placeHolderStyle
            }]
            },
            {
                name: '上海',
                        type: 'pie',
                    clockWise: false,
                    center: ['50%', '42%'],
                radius: ['49%', '60%'],
                itemStyle: dataStyle,
                        hoverAnimation: false,
                    data: [{
                value: 70,
                        name: '02'
            }, {
                value: 30,
                        name: 'invisible',
                        tooltip: {show: false},
                itemStyle: placeHolderStyle
            }]
            },
            {
                name: '广东',
                        type: 'pie',
                    clockWise: false,
                    hoverAnimation: false,
                    center: ['50%', '42%'],
                radius: ['39%', '50%'],
                itemStyle: dataStyle,
                        data: [{
                value: 65,
                        name: '03'
            }, {
                value: 35,
                        name: 'invisible',
                        tooltip: {show: false},
                itemStyle: placeHolderStyle
            }]
            },
            {
                name: '北京',
                        type: 'pie',
                    clockWise: false,
                    hoverAnimation: false,
                    center: ['50%', '42%'],
                radius: ['29%', '40%'],
                itemStyle: dataStyle,
                        data: [{
                value: 60,
                        name: '04'
            }, {
                value: 40,
                        name: 'invisible',
                        tooltip: {show: false},
                itemStyle: placeHolderStyle
            }]
            },
            {
                name: '深圳',
                        type: 'pie',
                    clockWise: false,
                    hoverAnimation: false,
                    center: ['50%', '42%'],
                radius: ['20%', '30%'],
                itemStyle: dataStyle,
                        data: [{
                value: 50,
                        name: '05'
            }, {
                value: 50,
                        name: 'invisible',
                        tooltip: {show: false},
                itemStyle: placeHolderStyle
            }]
            }, ]
};

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
            window.addEventListener("resize",function(){
                myChart.resize();
            });
        }
        function echarts_31() {
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('fb1'));
            option = {

                    title: [{
                //todo  年龄数据的展示
                text: '年龄分布',
                        left: 'center',
                        textStyle: {
                    color: '#fff',
                            fontSize:'16'
                }

            }],
            tooltip: {
                trigger: 'item',
                        formatter: "{a} <br/>{b}: {c} ({d}%)",
                        position:function(p){   //其中p为当前鼠标的位置
                    return [p[0] + 10, p[1] - 10];
                }
            },
            legend: {

                top:'70%',
                        itemWidth: 10,
                        itemHeight: 10,
                        data:['0岁以下','20-29岁','30-39岁','40-49岁','50岁以上'],
                textStyle: {
                    color: 'rgba(255,255,255,.5)',
                            fontSize:'12',
                }
            },
            series: [
            {
                name:'年龄分布',
                        type:'pie',
                    center: ['50%', '42%'],
                radius: ['40%', '60%'],
                color: ['#065aab', '#066eab', '#0682ab', '#0696ab', '#06a0ab','#06b4ab','#06c8ab','#06dcab','#06f0ab'],
                label: {show:false},
                labelLine: {show:false},
                data:[
                {value:1, name:'0岁以下'},
                {value:4, name:'20-29岁'},
                {value:2, name:'30-39岁'},
                {value:2, name:'40-49岁'},
                {value:1, name:'50岁以上'},
            ]
            }
    ]
};

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
            window.addEventListener("resize",function(){
                myChart.resize();
            });
        }
        function echarts_32() {
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('fb2'));
            option = {

                    title: [{
                text: '职业分布',
                        left: 'center',
                        textStyle: {
                    color: '#fff',
                            fontSize:'16'
                }

            }],
            tooltip: {
                trigger: 'item',
                        formatter: "{a} <br/>{b}: {c} ({d}%)",
                        position:function(p){   //其中p为当前鼠标的位置
                    return [p[0] + 10, p[1] - 10];
                }
            },
            legend: {

                top:'70%',
                        itemWidth: 10,
                        itemHeight: 10,
                        data:['电子商务','教育','IT/互联网','金融','学生','其他'],
                textStyle: {
                    color: 'rgba(255,255,255,.5)',
                            fontSize:'12',
                }
            },
            series: [
            {
                name:'年龄分布',
                        type:'pie',
                    center: ['50%', '42%'],
                radius: ['40%', '60%'],
                color: ['#065aab', '#066eab', '#0682ab', '#0696ab', '#06a0ab','#06b4ab','#06c8ab','#06dcab','#06f0ab'],
                label: {show:false},
                labelLine: {show:false},
                data:[
                {value:5, name:'电子商务'},
                {value:1, name:'教育'},
                {value:6, name:'IT/互联网'},
                {value:2, name:'金融'},
                {value:1, name:'学生'},
                {value:1, name:'其他'},
            ]
            }
    ]
};

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
            window.addEventListener("resize",function(){
                myChart.resize();
            });
        }
        function echarts_33() {
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('fb3'));
            option = {
                    title: [{
                text: '兴趣分布',
                        left: 'center',
                        textStyle: {
                    color: '#fff',
                            fontSize:'16'
                }

            }],
            tooltip: {
                trigger: 'item',
                        formatter: "{a} <br/>{b}: {c} ({d}%)",
                        position:function(p){   //其中p为当前鼠标的位置
                    return [p[0] + 10, p[1] - 10];
                }
            },
            legend: {
                top:'70%',
                        itemWidth: 10,
                        itemHeight: 10,
                        data:['汽车','旅游','财经','教育','软件','其他'],
                textStyle: {
                    color: 'rgba(255,255,255,.5)',
                            fontSize:'12',
                }
            },
            series: [
            {
                name:'兴趣分布',
                        type:'pie',
                    center: ['50%', '42%'],
                radius: ['40%', '60%'],
                color: ['#065aab', '#066eab', '#0682ab', '#0696ab', '#06a0ab','#06b4ab','#06c8ab','#06dcab','#06f0ab'],
                label: {show:false},
                labelLine: {show:false},
                data:[
                {value:2, name:'汽车'},
                {value:3, name:'旅游'},
                {value:1, name:'财经'},
                {value:4, name:'教育'},
                {value:8, name:'软件'},
                {value:1, name:'其他'},
            ]
            }
    ]
};

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
            window.addEventListener("resize",function(){
                myChart.resize();
            });
        }


    })*/


















