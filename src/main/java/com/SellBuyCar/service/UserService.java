package com.SellBuyCar.service;

import com.SellBuyCar.exception.UserNotFoundException;

public interface UserService {

    /**
     *
     * Method: forgotPass
     *
     * Description: Sends a password reset email to the specified email address.
     *
     * @param email            The email address of the user requesting password
     *                         reset.
     *
     * @param resetPasswordink The password reset link to be included in the email.
     *
     * @throws UserNotFoundException If the user is not found in the system.
     */
    void forgotPass(String email, String resetPasswordink) throws UserNotFoundException;

    /**
     *
     * Updates the reset password token for a user based on their email.
     *
     * @param token The new reset password token to be set for the user.
     *
     * @param email The email of the user whose reset password token needs to be
     *              updated.
     *
     * @throws UserNotFoundException If no user is found with the provided email.
     */
    void updateResetPassword(String token, String email) throws UserNotFoundException;

    /**
     *
     * Updates the password for a user based on the provided reset password token.
     *
     * @param token       The reset password token used to identify the user.
     *
     * @param newPassword The new password to be set for the user.
     */
    void updatePassword(String token, String newPassword);

}
