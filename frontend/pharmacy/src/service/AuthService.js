import axios from "axios";


const API_URL = "http://localhost:8080";

class AuthService {
	login(credentials){
		return axios({
		  method: 'post',
		  url: `${API_URL}/api/auth/login`,
		  data: credentials
		}).then(response => {
			if (response.data.accessToken) {
			  localStorage.setItem('userToken', JSON.stringify(response.data));
			  axios.defaults.headers.common['Authorization'] = `Bearer ${response.data.accessToken}`;
			  console.log(axios.defaults.headers.common['Authorization']);
			  this.setCurrentUser(response.data.accessToken).then(response => {
				window.location.href = "/";
			  });
				window.location.href = "/";

			  	return response.data;
			};
			//response.data.redirect = "/login";
			return response.data;
		})
		
	}
	
	logout(){
		axios.defaults.headers.common['Authorization'] = null;
		localStorage.removeItem('userToken');
		localStorage.removeItem('currentUser');
	}
	
	setCurrentUser(token){
		axios.defaults.headers.common['Authorization'] = "Bearer " + token;

		let config = {}
		if (token != undefined) {
			config.headers = {Authorization: "Bearer " + token, A: "Bearer " + token};
		}
		console.log(config);

		return axios.get(`${API_URL}/api/auth/getLoggedIn`, config).then(response => {
			localStorage.setItem('currentUser', JSON.stringify(response.data));
			return response.data;
		})
	}

	getCurrentUser(){
		let user = JSON.parse(localStorage.getItem('currentUser'));
		return user;
	}

	getUserRole() {
		// TODO: implement
	}

	getLoggedIdOrLogout() {
		let user = this.getCurrentUser();
		if (user == null) {
		  this.logout();
		  window.location.href = "/login";
		  return null;
		}
  
  
		return user.id;
	}
}


export default new AuthService();
