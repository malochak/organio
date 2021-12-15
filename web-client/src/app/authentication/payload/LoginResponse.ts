import {FieldsErrors} from "../../utils/FieldsErrors";

export interface LoginResponse {
  success: boolean,
  errors: FieldsErrors
}
