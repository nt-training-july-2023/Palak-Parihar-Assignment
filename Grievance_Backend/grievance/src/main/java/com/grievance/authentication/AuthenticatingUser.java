package com.grievance.authentication;

/**
 * Class to authenticate a user.
 */
public interface AuthenticatingUser {
  /**
   * method to check if user is authorised or not.
   * @param email
   * @param password
   * @return boolean
   */
  Boolean checkIfUserExists(String email, String password);

  /**
   * method to check if user is admin or not.
   * @param email
   * @param password
   * @return boolean
   */
  Boolean checkIfUserIsAdmin(String email, String password);
}
