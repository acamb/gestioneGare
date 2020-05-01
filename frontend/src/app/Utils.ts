import { environment } from "../environments/environment";

export function extractData(res){
    return res.json() || {};
  }
  
 export function getServer(): string{
    let getUrl = window.location;
    let baseUrl = getUrl .protocol + "//" + getUrl.host + environment.context
    return baseUrl;
  }
  