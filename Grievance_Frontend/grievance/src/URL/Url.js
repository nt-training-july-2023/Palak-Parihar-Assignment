const BASE_URL = "http://localhost:8080"

export const LOGIN_USER_URL = BASE_URL + "/login"
export const FETCH_ALL_USERS_URL = BASE_URL + "/listAllEmployees";
export const SAVE_NEW_EMPLOYEE_URL = BASE_URL + "/saveEmployee";

export const DEPARTMENT_BASE_URL = BASE_URL + "/department";
export const FETCH_ALL_DEPARTMENTS_URL = DEPARTMENT_BASE_URL + '/listDepartments'
export const GENERATE_NEW_DEPARTMENTS_URL = DEPARTMENT_BASE_URL + '/save'

export const TICKET_BASE_URL = BASE_URL + "/ticket";
export const FETCH_ALL_TICKETS_URL = TICKET_BASE_URL + "/listAllTickets";
export const GENERATE_NEW_TICKET_URL = TICKET_BASE_URL + '/addTicket'

