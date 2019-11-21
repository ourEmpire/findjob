import '../sass/footer.scss';

export function copyright(){
    let template = `\n %c ⛱ Find Job %c 🍑 job.ourfor.top \n\n`;
    let style = {
        start: "color: #fadfa3; background: #030307; padding:5px 0;",
        end: "color: white; background: #dc1049; padding:5px 0;"
    };
    console.log(template,style.start,style.end);
}

export function CopyRight() {
    return (
        <div className="copyright">
            <span>💐 Find Job</span>
            <span>🍒 job.ourfor.top</span>
        </div>
    );
}
