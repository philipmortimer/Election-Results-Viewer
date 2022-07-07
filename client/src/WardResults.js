import React from 'react';

import Card from 'react-bootstrap/Card';

class WardResults extends React.Component {

    constructor(props) {
      super(props);
      this.state = {
        isLoaded: false,
        isError: false,
        item: {}
      }
    }
  
    _fetchData() {
      const url = "http://localhost:8000/api/ward/results/" + this.props.wardId;
      fetch(url)
      .then(r => {
        if (!r.ok) {
          const e = "Attempting to load " + r.url + " got status " + r.status + ".";
          this.setState({isLoaded: true, isError: true, errmsg: e})
        }
        return r.json()
      })
      .then (
        (result) => {
          if (!this.state.isError) {
            this.setState({isLoaded: true, item: result})
          }
        },
        (error) => {
          this.setState({isLoaded: true, isError: true, errmsg: "Network error"})
        }
      )
    }

    componentDidUpdate(oldProps) {
      if (this.props.wardId !== oldProps.wardId) {
        this.setState({isLoaded: false});
        this._fetchData();
      }
    }

    componentDidMount() {
      this._fetchData();
    }

    winner() {
      return this.state.item.results.length === 0? "" : this.state.item.results[0].id;
    }
  
    render() {
      if (this.state.isLoaded === false) {
        return (
          <Card>
            <Card.Body>
              <Card.Text>
                Loading ...
              </Card.Text>
            </Card.Body>
          </Card>
        )
      }
      if (this.state.isError) {
        return (
          <Card>
            <Card.Body>
              <Card.Title>Error</Card.Title>
              <Card.Text>
                An error occurred.
              </Card.Text>
            </Card.Body>
          </Card>
        )
      }
      return (
        <Card>
          <Card.Body>
            <Card.Title>Election Results</Card.Title>
            <Card.Subtitle className="mb-2 text-muted">for {this.state.item.wardName}</Card.Subtitle>
            <table className="table table-sm">
              <thead>
                <tr>
                  <th>Candidate</th>
                  <th style={{textAlign: "right"}}>Party</th>
                  <th style={{textAlign: "right"}}>Votes</th>
                  <th style={{textAlign: "right"}}>Vote %</th>
                </tr>
              </thead>
              <tbody>
              {
                this.state.item.results.map(i =>
                  <tr key={i.id} win={this.winner()===i.id}>
                    <td>{i.name}</td>
                    <td style={{textAlign: "right"}}>{i.party}</td>
                    <td style={{textAlign: "right"}}>{i.votes}</td>
                    <td style={{textAlign: "right"}}>{i.votePercentString}</td>
                  </tr>
                )
              }
              </tbody>
            </table>
            <div class="election-facts">    
              <span><strong>Total Votes: </strong>{this.state.item.totalVotes}</span>     
              <span><strong>Registered Voters: </strong>{this.state.item.electorate}</span>  
              <span><strong>Turnout: </strong>{this.state.item.turnoutString}</span>
            </div>
          </Card.Body>
        </Card>
      )
    }
}

export default WardResults;
