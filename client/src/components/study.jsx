import data from '../config/product_manager.json';

class Study extends Component {

    constructor(props){
        super(props);

        let option = {
            title: {
                text: '学习路线参考',
                left: 'center',
                top: 20,
                textStyle: {
                    color: '#ccc'
                }
            },
            tooltip: {
                trigger: 'item',
                triggerOn: 'mousemove'
            },
            series: [
                {
                    type: 'tree',
                    data: [props.data],
                    top: '1%',
                    left: '14%',
                    bottom: '1%',
                    right: '20%',
                    symbolSize: 14,
                    label: {
                        normal: {
                            position: 'left',
                            verticalAlign: 'middle',
                            align: 'right',
                            fontSize: 14
                        }
                    },
                    leaves: {
                        label: {
                            normal: {
                                position: 'right',
                                verticalAlign: 'middle',
                                align: 'left'
                            }
                        }
                    },
                    expandAndCollapse: true,
                    animationDuration: 550,
                    animationDurationUpdate: 750
                }
            ]
        };


        this.state = {
            dom: React.createRef(),
            option
        };
    }

    componentDidMount(){
        let me = echarts.init(this.state.dom.current);
        this.setState(state => ({
            me
        }));
        me.setOption(this.state.option);
    }

    componentWillReceiveProps(){
        let option = this.state.option;
        let data = this.props.data;
        let origin = option.series[0].data[0];
        if(data.name !== origin.name){
            option.series[0].data = [data];
            this.state.me.setOption(option);
        }
    }

    render(){
        return(
        <div id="study">
            <div ref={this.state.dom} className="study-ways" style={{height: 400,width: 640}}>
            </div>
        </div>
        );

    }
}


class Analyse extends Component {
    constructor(props){
        super(props);

        let option = {
            title: {
                text: '2016 ~ 2019 IT行业职位需求百分比',
                left: 'center',
                top: 20,
                textStyle: {
                    color: '#595959',
                    fontSize: 13
                }
            },
            legend: {},
            tooltip: {},
            dataset: {
                source: [
                    ['product', '2012', '2013', '2014', '2015', '2016', '2017'],
                    ['UI设计师', 41.1, 30.4, 65.1, 53.3, 83.8, 98.7],
                    ['项目经理', 86.5, 92.1, 85.7, 83.1, 73.4, 55.1],
                    ['软件开发工程师', 24.1, 67.2, 79.5, 86.4, 65.2, 82.5],
                    ['测试工程师', 55.2, 67.1, 69.2, 72.4, 53.9, 39.1]
                ]
            },
            series: [{
                type: 'pie',
                radius: 60,
                center: ['25%', '30%']
                // No encode specified, by default, it is '2012'.
            }, {
                type: 'pie',
                radius: 60,
                center: ['75%', '30%'],
                encode: {
                    itemName: 'product',
                    value: '2013'
                }
            }, {
                type: 'pie',
                radius: 60,
                center: ['25%', '75%'],
                encode: {
                    itemName: 'product',
                    value: '2014'
                }
            }, {
                type: 'pie',
                radius: 60,
                center: ['75%', '75%'],
                encode: {
                    itemName: 'product',
                    value: '2015'
                }
            }]
        };

        this.state = {
            dom: React.createRef(),
            option
        };
    }

    componentDidMount(){
        let me = echarts.init(this.state.dom.current);
        me.setOption(this.state.option);
    }
        

    render(){
        return (
        <div id="analys">
            <div ref={this.state.dom} className="study-ways" style={{height: 400,width: 640}}>
            </div>
        </div>
        );
    }
}


export default Study;
export { Analyse }