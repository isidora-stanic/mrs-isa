import axios from "axios";


const API_URL = "api/orders";

class OrderDataService {
    getOrders(idAdmina) {
        return axios.get(API_URL + "/" + idAdmina)
        .catch(function (error) {
          if (error.response) {
            console.log(error.response.data);
          } else if (error.request) {
            console.log(error.request);
          }
          console.log("Error");
          console.log(error.config);
      });
    }

    getAvailableOrders(){
      return axios.get(`${API_URL}/availableOrders`);
    }

    addOrder(idAdminaApoteke, lekovi, kraj) {
      return axios({
        method: 'post',
        url: API_URL + "/add/" + idAdminaApoteke,
        data: {"medicines": lekovi, "endDate": kraj}
      }).catch(function (error) {
        if (error.response) {
            console.log(error.response.data);
        } else if (error.request) {
            console.log(error.request);
        }
        console.log("Error");
        console.log(error.config);
    });
    }

    deleteOrder(narudzbina) {
      return axios({
          method: 'post',
          url: API_URL + "/delete",
          data: narudzbina
      }).catch(function (error) {
          if (error.response) {
            console.log(error.response.data);
          } else if (error.request) {
            console.log(error.request);
          }
          console.log("Error");
          console.log(error.config);
      });
  }

  findOneOrder(order) {
    return axios({
        method: 'post',
        url: API_URL + "/findOne",
        data: order
    }).catch(function (error) {
        if (error.response) {
          console.log(error.response.data);
        } else if (error.request) {
          console.log(error.request);
        }
        console.log("Error");
        console.log(error.config);
    });
  }

  editOrder(order) {
    return axios({
        method: 'post',
        url: API_URL + "/edit",
        data: order
    }).catch(function (error) {
        if (error.response) {
          console.log(error.response.data);
        } else if (error.request) {
          console.log(error.request);
        }
        console.log("Error");
        console.log(error.config);
    });
  }

  chooseOffer(offer) {
    return axios({
        method: 'post',
        url: API_URL + "/choose",
        data: offer
    }).catch(function (error) {
        if (error.response) {
          console.log(error.response.data);
        } else if (error.request) {
          console.log(error.request);
        }
        console.log("Error");
        console.log(error.config);
    });
  }

}


export default new OrderDataService();