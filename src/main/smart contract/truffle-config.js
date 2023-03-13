module.exports = {
  compilers:{
    solc:{
      version:"^0.8.0"
    }
  },
  // See <http://truffleframework.com/docs/advanced/configuration>
  // for more about customizing your Truffle configuration!
  networks: {
    development: {
      host: "192.168.1.250",
      port: 7545,
      network_id: "*" // Match any network id
    },
    develop: {
      host: "192.168.1.250",
      port: 7545,
      gas: 10000000000
    }
  }
};
