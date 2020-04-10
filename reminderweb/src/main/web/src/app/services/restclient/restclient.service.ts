import { Injectable } from "@angular/core";
import axios from "axios";
import { SpinnerService } from "../spinner/spinner.service";
import { NgxSpinnerService } from "ngx-spinner";
@Injectable({
  providedIn: "root"
})
export class RestclientService {
  constructor(
    private spinner: SpinnerService
  ) {}

  async doUserAuth(
    formData: FormData,
    url,
    spin: NgxSpinnerService,
    spinnername
  ) {
    const myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/x-www-form-urlencoded");
    myHeaders.append("Access-Control-Allow-Origin", "*");
    myHeaders.append("Access-Control-Allow-Methods", "POST");
    const resp = { response: null, error: null };

    // console.log(ddata);
    await axios({
      method: "POST",
      url: url,
      data: formData,
      headers: myHeaders,
      withCredentials: true
    })
      .then(response => {
        // console.log(response);
        // console.log(response.headers["Set-Cookie"]);
        if (response.status !== 201 && response.status !== 200) {
          resp.error = response.data;
        } else {
          resp.response = "200";
        }
      })
      .catch(error => {
        if (error == null) {
          return (resp.error = "NETWORK ERROR");
        }
        if (error.response != null) {
          if (error.response.data.status === 401) {
            resp.error = "Invalid Credentials"
          } else {
            resp.error = error.response.data.message;
          }
        } else {
          resp.error = error;
        }
      });

    return resp;
  }

  async funcGet(link, spin: NgxSpinnerService, spinnername) {
    this.spinner.startSpinner(spinnername, spin);
    // console.log('link being loaded is : ' + link);
    const resp = { response: null, error: null };
    await axios({
      method: "GET",
      url: link + "?language=en",
      withCredentials: true
    })
      .then(response => {
        if (response.status == 201 || response.status == 200) {
          // console.log(response);
          resp.response = response.data;
        } else {
          // console.log(response.data);
          resp.error = response.data;
        }
      })
      .catch(error => {
        console.log(error);
        // // console.log(error.message);
        // // console.log(error.response);
        if (error == null) {
          return (resp.error = "NETWORK ERROR");
        }
        if (error.response != null) {
          // console.log(error.response);
          if (error.response.data.status == 401) {
            resp.error = "SESSION TIMEOUT"
          } else if (
            error.response.data.message == null ||
            error.response.data.message == "No message available"
          ) {
            resp.error = error.response.data.error;
          } else {
            resp.error = error.response.data.message;
          }
        } else {
          resp.error = error;
        }
      });
    this.spinner.stopSpinner(spinnername, spin);
    return resp;
  }

  async funcGetWithoutSpinner(link) {
    // console.log('link being loaded is : ' + link);
    const resp = { response: null, error: null };
    await axios({
      method: "GET",
      url: link + "?language=en",
      withCredentials: true
    })
      .then(response => {
        if (response.status == 201 || response.status == 200) {
          // console.log(response);
          resp.response = response.data;
        } else {
          // console.log(response.data);
          resp.error = response.data;
        }
      })
      .catch(error => {
        console.log(error);
        // // console.log(error.message);
        // // console.log(error.response);
        if (error == null) {
          return (resp.error = "NETWORK ERROR");
        }
        if (error.response != null) {
          // console.log(error.response);
          if (error.response.data.status == 401) {
            resp.error = "SESSION TIMEOUT"
          } else if (
            error.response.data.message == null ||
            error.response.data.message == "No message available"
          ) {
            resp.error = error.response.data.error;
          } else {
            resp.error = error.response.data.message;
          }
        } else {
          resp.error = error;
        }
      });
    return resp;
  }

  async funcPost(link, data, spin: NgxSpinnerService, spinnername) {
    this.spinner.startSpinner(spinnername, spin);
    const myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    myHeaders.append("Access-Control-Allow-Origin", "*");
    myHeaders.append("Access-Control-Allow-Methods", "POST");
    const resp = { response: null, error: null };
    await axios({
      method: "POST",
      url: link + "?language=en",
      data: data,
      headers: myHeaders,
      withCredentials: true
    })
      .then(response => {
        // console.log(response);
        if (response.status == 201 || response.status == 200) {
          resp.response = response.data;
        } else {
          resp.error = response.data;
        }
      })
      .catch(error => {
        if (error == null) {
          return (resp.error = "NETWORK ERROR");
        }
        if (error.response != null) {
          if (error.response.data.status === 401) {
            resp.error = "SESSION TIMEOUT"
          } else if (
            error.response.data.message == null ||
            error.response.data.message == "No message available"
          ) {
            resp.error = error.response.data.error;
          } else {
            resp.error = error.response.data.message;
          }
        } else {
          resp.error = error;
        }
      });
    this.spinner.stopSpinner(spinnername, spin);
    return resp;
  }

  async fetchDoc(link, spin: NgxSpinnerService, spinnername) {
    this.spinner.startSpinner(spinnername, spin);
    const myHeaders = new Headers();
    const resp = { response: null, error: null };
    await axios({
      method: "GET",
      url: link + "?language=en",
      headers: myHeaders,
      responseType: "blob",
      withCredentials: true
    })
      .then(response => {
        if (response.status == 201 || response.status == 200) {
          resp.response = response.data;
        } else {
          resp.error = response.data;
        }
      })
      .catch(error => {
        if (error == null) {
          return (resp.error = "NETWORK ERROR");
        }
        if (error.response != null) {
          if (error.response.data.status === 401) {
            resp.error = "SESSION TIMEOUT"
          } else if (
            error.response.data.message == null ||
            error.response.data.message == "No message available"
          ) {
            resp.error = error.response.data.error;
          } else {
            resp.error = error.response.data.message;
          }
        } else {
          resp.error = error;
        }
      });
    this.spinner.stopSpinner(spinnername, spin);
    return resp;
  }

  async funcPostDoc(link, data, spin: NgxSpinnerService, spinnername, file) {
    // console.log("Uploading...: " + this.files.length);
    // console.log(this.files);
    this.spinner.startSpinner(spinnername, spin);
    const resp = { response: null, error: null };
    const myHeaders = new Headers();
    myHeaders.append("Content-Type", undefined);
    myHeaders.append("Access-Control-Allow-Origin", "*");
    myHeaders.append("Access-Control-Allow-Methods", "POST");
      console.log(file);
    const formData = new FormData();
    formData.append("file", file, file.name);
    formData.append(
      "model",
      new Blob([JSON.stringify(data)], { type: "application/json" })
    );

    await axios({
      method: "POST",
      url: link + "?language=en",
      data: formData,
      headers: myHeaders,
      withCredentials: true
    })
      .then(response => {
        // console.log(response);
        if (response.status == 201 || response.status == 200) {
          resp.response = response.data;
        } else {
          resp.error = response.data;
        }
      })
      .catch(error => {
        if (error == null) {
          return (resp.error = "NETWORK ERROR");
        }
        if (error.response != null) {
          if (error.response.data.status === 401) {
            resp.error = "SESSION TIMEOUT"
          } else if (
            error.response.data.message == null ||
            error.response.data.message == "No message available"
          ) {
            resp.error = error.response.data.error;
          } else {
            resp.error = error.response.data.message;
          }
        } else {
          resp.error = error;
        }
      });
    this.spinner.stopSpinner(spinnername, spin);
    return resp;
  }
}
