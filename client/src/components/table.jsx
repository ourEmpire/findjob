import '../sass/table.scss';
function Table(props) {
    let items = props.items;
    let content = items.map( (v,i) => {
        return <Tr key={`tr-${i}`} data={v} />
    })
    return (
        <table className="table-body">
            {content}
        </table>
    );
}

function Tr(props){
    let data = props.data;
    return (
        <tr key={`table-tr-${data.name}`} className="table-tr">
            <td>{data.name}</td>
            <td><a href={data.link}>{data.link}</a></td>
        </tr>
    );
}

export { Table, Tr }
export default Table;