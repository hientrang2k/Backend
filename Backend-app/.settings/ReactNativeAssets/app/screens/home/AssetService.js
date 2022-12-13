import axios from "axios";
import ConstantList from "../../appConfig";
const API_PATH =
  ConstantList.API_ENPOINT + "/api/asset" + ConstantList.URL_PREFIX;
const API_PATH_VOUCHER =
  ConstantList.API_ENPOINT + "/api/voucher" + ConstantList.URL_PREFIX;
const API_PATH_MAINTAINREQUEST =
  ConstantList.API_ENPOINT + "/api/maintainRequest" + ConstantList.URL_PREFIX;
const API_PATH_DOCUMENT = ConstantList.API_ENPOINT + "/api/asset_document";
const API_PATH_ASSET_DEPARTMENT =
  ConstantList.API_ENPOINT + "/api/assetDepartment" + ConstantList.URL_PREFIX;
const API_PATH_PRODUCT =
  ConstantList.API_ENPOINT + "/api/product" + ConstantList.URL_PREFIX;
const API_PATH_ASSET_STATUS =
  ConstantList.API_ENPOINT + "/api/assetstatus" + ConstantList.URL_PREFIX;

//Asset
export const getNewCode = () => {
  return axios.get(API_PATH + "/addNew/getNewCode");
};

export const searchByPage = (searchObject) => {
  var url = API_PATH + "/searchByPage";
  return axios.post(url, searchObject);
};

export const deleteItem = (id) => {
  return axios.delete(API_PATH + "/" + id);
};

export const checkDeleteAsset = (id) => {
  return axios.get(API_PATH + "/checkDeleteById/" + id);
};

export const getItemById = (id) => {
  return axios.get(API_PATH + "/" + id);
};
export const checkCode = (id, code) => {
  const config = { params: { id: id, code: code } };
  var url = API_PATH + "/checkCode";
  return axios.get(url, config);
};

export const codeWasUsed = (asset) => {
  return axios.post(API_PATH + "/check/codeWasUsed", asset);
};

export const createAsset = (asset) => {
  return axios.post(API_PATH, asset);
};
export const updateAsset = (asset) => {
  return axios.put(API_PATH + "/" + asset.id, asset);
};

export const getAllAssets = () => {
  return axios.get(API_PATH + "/1/100000");
};

// ASSET_DEPARTMENT
export const getListManagementDepartment = () => {
  return axios.get(API_PATH_ASSET_DEPARTMENT + "/getListManagementDepartment");
};

export const getListUserDepartment = (SeachObjectct) => {
  var url = API_PATH_ASSET_DEPARTMENT + "/getListUserDepartment";
  return axios.post(url, SeachObjectct);
};

export const getAllProducts = () => {
  return axios.get(API_PATH_PRODUCT + "/getall");
};
// export const getAllCountryOrigins = () => {
//   return axios.get(ConstantList.API_ENPOINT + "/api/country/1/100000");
// };
export const getAllstatuss = () => {
  return axios.get(API_PATH_ASSET_STATUS + "/1/100000");
};
// export const getAllmanufacturers = () => {
//   return axios.get(ConstantList.API_ENPOINT + "/api/commonobject/1/100000");
// };

export const getUserById = (id) => {
  return axios.get("/api/user", { data: id });
};

export const exportToExcel = (asset) => {
  return axios({
    method: "post",
    url: ConstantList.API_ENPOINT + "/api/fileDownload/assetToExcel",
    data: asset,
    responseType: "blob",
  });
};

export const exportExampleImportExcel = (asset) => {
  return axios({
    method: "get",
    url: ConstantList.API_ENPOINT + "/api/fileDownload/exportExampleAsset",
    data: asset,
    responseType: "blob",
  });
};

export const historyOfAllocationTransfer = (assetObjectId) => {
  return axios.post(
    API_PATH_VOUCHER + "/historyOfAllocationTransfer",
    assetObjectId
  );
};

export const historyOfBrokenMessageAndRepair = (assetObjectId) => {
  return axios.post(
    API_PATH_MAINTAINREQUEST + "/historyOfBrokenMessageAndRepair",
    assetObjectId
  );
};

// API_PATH_DOCUMENT
export const searchByPageAssetDocument = (assetDocumentObject) => {
  return axios.post(API_PATH_DOCUMENT + "/searchByPage", assetDocumentObject);
};

export const createAssetDocument = (assetDocument) => {
  return axios.post(API_PATH_DOCUMENT, assetDocument);
};

export const getAssetDocumentById = (assetDocumentId) => {
  return axios.get(API_PATH_DOCUMENT + "/" + assetDocumentId);
};

export const deleteAssetDocumentById = (assetDocumentId) => {
  return axios.delete(API_PATH_DOCUMENT + "/" + assetDocumentId);
};

export const updateAssetDocumentById = (assetDocument, assetDocumentId) => {
  return axios.put(API_PATH_DOCUMENT + "/" + assetDocumentId, assetDocument);
};

export const getNewCodeAssetDocument = () => {
  return axios.get(API_PATH_DOCUMENT + "/addNew/getNewCode");
};
