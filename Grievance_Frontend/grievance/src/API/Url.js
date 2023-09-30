export const BASE_URL = `${process.env.REACT_APP_BASE_URL}`

export const EMPLOYEE_BASE_URL = BASE_URL + "/employee";
export const LOGIN_USER_URL = EMPLOYEE_BASE_URL + "/login";
export const CHANGE_PASSWORD_URL = EMPLOYEE_BASE_URL + "/changePassword";
export const FETCH_ALL_USERS_URL = EMPLOYEE_BASE_URL + "/list";
export const SAVE_NEW_EMPLOYEE_URL = EMPLOYEE_BASE_URL + "/save";
export const DELETE_EMPLOYEE_URL = EMPLOYEE_BASE_URL + "/delete"

export const DEPARTMENT_BASE_URL = BASE_URL + "/department";
export const FETCH_ALL_DEPARTMENTS_URL = DEPARTMENT_BASE_URL + '/list'
export const GENERATE_NEW_DEPARTMENTS_URL = DEPARTMENT_BASE_URL + '/save'
export const DELETE_DEPARTMENT_URL = DEPARTMENT_BASE_URL + '/delete'

export const TICKET_BASE_URL = BASE_URL + "/ticket";
export const FETCH_ALL_TICKETS_URL = TICKET_BASE_URL + "/list";
export const GENERATE_NEW_TICKET_URL = TICKET_BASE_URL + '/save'
export const GET_TICKET_BY_ID_URL = TICKET_BASE_URL + "/get"
export const UPDATE_TICKET_BY_ID_URL = TICKET_BASE_URL + "/update"

