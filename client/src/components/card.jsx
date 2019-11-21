import '../sass/card.scss';
import job1 from '../image/job_1.jpeg';
import job2 from '../image/job_2.jpg';
import job3 from '../image/job_3.jpg';
import job4 from '../image/job_4.gif';
import data from '../config/jobs.yml'

let jobs = [job1,job2,job3,job4];

let Card = props => {

    return (
        <div className="job-card">
            <img src={props.pic} />
            <div className="job-desc">
                <p>{props.data.name}</p>
                <section>
                    {props.data.info}
                </section>
            </div>
        </div>
    );
}

let Cards = props => {
    let list = new Array(4).fill(1);
    let cards = list.map( (v,i) => {
        return <Card key={`card-${i}`} pic={jobs[i]} data={data.jobs[i]}/>
    });
    return (
        <div className="job-cards">
            {cards}
        </div>
    );
}

export { Card, Cards };