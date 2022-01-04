import {FieldsErrors} from "../../utils/FieldsErrors";

export interface Response {
  success: boolean,
  errors: Array<FieldsErrors>
}
