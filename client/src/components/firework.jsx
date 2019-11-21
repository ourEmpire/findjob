import init from './fireworks.js';

class Firework extends Component {
    constructor(props){
        super(props);
        this.state = {
            dom: React.createRef()
        }
    }
    componentDidMount(){
        init(this.state.dom.current);
    }
    render(){
        let style = {
            position: 'fixed',
            left: '0',
            top: '0',
            zIndex: '1',
            pointerEvents: 'none'
        };
        return <canvas ref={this.state.dom} className='fireworks' style={style}></canvas>
    }
}

export default Firework;