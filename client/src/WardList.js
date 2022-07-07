import React from 'react';

import Button from 'react-bootstrap/Button';
import Card from 'react-bootstrap/Card';

class WardList extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      loaded: "no", // options: no, yes, error
      item: null
    }
  }

  componentDidMount() {
    this._fetchData();
  }

  wards() {
    return this.state.item.map(i =>
      <li key={i.id}><Button className="pt-0 pb-0" variant="link" 
          onClick={() => this.props.wardResultsRequested(i.id)}>{i.name}</Button></li>
      )
  }

  _fetchData() {
    const url = "http://localhost:8000/api/wards";
    fetch(url)
      .then(r => {
        if (!r.ok) {
          const e = "Attempting to load " + r.url + " got status " + r.status + ".";
          this.setState({loaded: "error", errmsg: e})
        }
        return r.json()
      })
      .then (
        (result) => {
          if (this.state.loaded !== "error") {
            this.setState({loaded: "yes", item: result})
          }
        },
        (error) => {
          this.setState({loaded: "error", errmsg: "Network error"})
        }
      )
  }

  render() {
    switch (this.state.loaded) {
      case "no":
        return (
          <Card>
            <Card.Body>
              <Card.Text>
                Loading ...
              </Card.Text>
            </Card.Body>
          </Card>
        )
      case "error":
        return (
          <Card>
            <Card.Body>
              <Card.Text>
                An error occurred.
              </Card.Text>
              <Card.Text>
                {this.state.errmsg}
              </Card.Text>
            </Card.Body>
          </Card>
        )
      default: // yes
        return (
          <Card>
            <Card.Body>
              <Card.Title>Bristol</Card.Title>
              <Card.Subtitle className="mb-2 text-muted">Election Results</Card.Subtitle>
              <Card.Text>
                {this.wards().length !== 0 ? 
                <b>Contains {this.wards().length} wards: </b>
                 : ""} </Card.Text>
              <ul>
              {this.wards()}
              </ul>
            </Card.Body>
          </Card>
        )
    }
  }
}

export default WardList;
