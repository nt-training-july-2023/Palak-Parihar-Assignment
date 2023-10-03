

export const inputValidity = (value, rules) => {
    let isValid = true;
    if (!rules) {
        return '';
    }

    if (rules.required) {
        isValid = value.trim() !== '' && isValid;
        if (!isValid) {
            return 'Field can not be empty'
        }
    }

    if (rules.minLength) {
        isValid = value.length >= rules.minLength && isValid;
        if (!isValid) {
            return 'Input must be greater or equals to length '+ rules.minLength;
        }
    }

    if (rules.maxLength) {
        isValid = value.length < rules.maxLength && isValid;
        if(!isValid){
            return 'Input must not exceed than length of '+rules.maxLength
        }
    }

    if (rules.isEmail) {
        const pattern = /^[a-z0-9+_.-]+@+nucleusteq.com$/;
        isValid = pattern.test(value) && isValid

        if(!isValid){
            return 'Email should of format example@nucleusteq.com'
        }

    }

    if (rules.isPassword) {
        const pattern = /^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{6,20}$/;
        isValid = pattern.test(value) && isValid
        if (!isValid) {
            return 'Password must include atleast a uppercase, a lowercase and a special character'
        }
    }

    if(rules.textOnly){
        const pattern = /^[a-zA-Z\s]*$/
        isValid = pattern.test(value) && isValid
        if(!isValid){
            return "Please provide text only"
        }
    }
    return '';
}

