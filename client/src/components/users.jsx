import { Timeline, Icon } from 'antd';
import { useSpring, animated } from 'react-spring'
import '../sass/users.scss';
import favicon from '../image/beauty.png'

const calc = (x, y) => [-(y - window.innerHeight / 2) / 20, (x - window.innerWidth / 2) / 20, 1.1]
const trans = (x, y, s) => `perspective(600px) rotateX(${x}deg) rotateY(${y}deg) scale(${s})`

function Card() {
  const [props, set] = useSpring(() => ({ xys: [0, 0, 1], config: { mass: 5, tension: 350, friction: 40 } }))
  return (
    <animated.div
      class="card"
      onMouseMove={({ clientX: x, clientY: y }) => set({ xys: calc(x, y) })}
      onMouseLeave={() => set({ xys: [0, 0, 1] })}
      style={{ transform: props.xys.interpolate(trans) }}
    />
  )
}

class Users extends Component {

    render(){
        return (
          <div className="page-users">
            <section className="user-info">
                <Card />
                <div>
                    <p>👏 Welcome back, Catalina!</p>
                </div>
            </section>
            <nav className="timeline">
                <Timeline mode="alternate">
                    <Timeline.Item>🚩 注册本站 2019-09-23</Timeline.Item>
                    <Timeline.Item color="green">📎 上传第一份简历 2019-10-03</Timeline.Item>
                    <Timeline.Item dot={<Icon type="clock-circle-o" style={{ fontSize: '16px' }} />}>
                      🧲 Mache 开始关注你
                    </Timeline.Item>
                    <Timeline.Item color="red">📮 您给 Goles 的留言得到回复 2019-10-16</Timeline.Item>
                    <Timeline.Item>🎈 更新账户信息 2019-10-18</Timeline.Item>
                    <Timeline.Item dot={<Icon type="clock-circle-o" style={{ fontSize: '16px' }} />}>
                        🎉 获取3点积分 2019-10-20
                    </Timeline.Item>
                </Timeline>                
            </nav>

          </div>  
        );
        
    }
}

function IsLogin(props){
    let token = localStorage.getItem('data_token')
    if(token){
        return <Users />
    } else {
        return <Tip />
    }
}

class Tip extends Component {
    componentDidMount(){
        setTimeout(e => {
            window.open('/login','_self');
        },2000)
    }
    render(){
        return (
            <div className="no-login">
				<section>
					<img src={favicon} />
					<h3>
						还未登录，即将跳转到登录页面
					</h3>
				</section>
			</div>            
        );
    }
}

export default IsLogin;