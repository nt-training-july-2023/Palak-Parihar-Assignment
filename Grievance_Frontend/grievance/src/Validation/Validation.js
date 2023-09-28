

export const inputValidity = (value, rules) => {
    console.log(rules)
    let isValid = true;
    if (!rules) {
        return '';
    }

    if (rules.required) {
        console.log('required')
        isValid = value.trim() !== '' && isValid;
        if (!isValid) {
            return 'Field can not be empty'
        }
    }

    if (rules.minLength) {
        isValid = value.length >= rules.minLength && isValid;
        if (!isValid) {
            return 'Password must be greater of equals to length 6'
        }
    }

    if (rules.maxLength) {
        isValid = value.length >= rules.minLength && isValid;
        if(!isValid){
            return 'Password must not exceed than length of 15'
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
        const pattern = /^[A-Za-z]+$/
        isValid = pattern.test(value) && isValid
        if(!isValid){
            return "Please provide text only"
        }
    }
    return '';
}

