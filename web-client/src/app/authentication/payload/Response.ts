import {FieldsErrors} from "../../utils/FieldsErrors";

export interface Response {
  success: boolean,
  errors: Array<FieldsErrors>
}

export const successResponse: Response = {
  success: true,
  errors: []
}

export const errorResponse = (response: any): Response => ({
  success: false,
  errors: mapErrorResponseToFields(response)
})

const mapErrorResponseToFields = (response: any): Array<FieldsErrors> =>
  response.error.subErrors.map((err: any) => ({field: err.field, message: err.message}))
