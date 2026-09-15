import { test, expect } from '@playwright/test';

// Zuv hereglegchiin medeelleer nevterch, amjilttai login bolon logout hiihig shalgana
test('амжилттай нэвтрэх', async ({ page }) => {

    // SauceDemo site-iig neene
    await page.goto('https://www.saucedemo.com');

    // Zuv username bolon password oruulna
    await page.getByPlaceholder('Username').fill('standard_user');
    await page.getByPlaceholder('Password').fill('secret_sauce');

    // Login tovchiig darna
    await page.getByRole('button', { name: 'Login' }).click();

    // Nevtersnii daraa Products heseg haragdaj baigaa esehiig shalgana
    await expect(page.getByText('Products', { exact: true })).toBeVisible();

    // Nevtersnii daraah URL zuv esehiig shalgana
    await expect(page).toHaveURL(/inventory.html/);

    // Menu neegeed Logout hiine
    await page.getByRole('button', { name: 'Open Menu' }).click();
    await page.getByRole('link', { name: 'Logout' }).click();

    // Logout hiisnii daraa Login tovch butsaj garsan esehiig shalgana
    await expect(page.getByRole('button', { name: 'Login' })).toBeVisible();

    // Logout hiisnii daraah URL zuv esehiig shalgana
    await expect(page).toHaveURL('https://www.saucedemo.com/');
});


// Buruu password ashiglah uyd login amjiltgui bolj,
// aldaanii message garsan esehiig shalgana
test('амжилтгүй нэвтрэх', async ({ page }) => {

    // SauceDemo site-iig neene
    await page.goto('https://www.saucedemo.com');

    // Username zuv, harin buruu password oruulna
    await page.getByPlaceholder('Username').fill('standard_user');
    await page.getByPlaceholder('Password').fill('wrong_password');

    // Login hiihig oroldono
    await page.getByRole('button', { name: 'Login' }).click();

    // Nevtreh bolomjgui bolson tuhai aldaanii message garsan esehiig shalgana
    await expect(
        page.getByText(
            'Epic sadface: Username and password do not match any user in this service',
            { exact: true }
        )
    ).toBeVisible();
});


// Amjilttai login hiisnii daraa buteegdehuuniig sagsand nemehig shalgana
test('нэвтэрсний дараа бараа сагслах', async ({ page }) => {

    // SauceDemo site-iig neene
    await page.goto('https://www.saucedemo.com');

    // Zuv hereglegchiin medeelleer nevterne
    await page.getByPlaceholder('Username').fill('standard_user');
    await page.getByPlaceholder('Password').fill('secret_sauce');
    await page.getByRole('button', { name: 'Login' }).click();

    // Products heseg neegdsen esehiig shalgana
    await expect(page.getByText('Products', { exact: true })).toBeVisible();

    // Ekhnii buteegdehuuniig sagsand nemne
    await page.getByRole('button', { name: 'Add to cart' }).first().click();

    // Sagsand 1 buteegdehuun nemegdsen esehiig shalgana
    await expect(page.getByTestId('shopping-cart-badge')).toHaveText('1');

    // Menu neegeed Logout hiine
    await page.getByRole('button', { name: 'Open Menu' }).click();
    await page.getByRole('link', { name: 'Logout' }).click();

    // Logout amjilttai bolson esehiig shalgana
    await expect(page.getByRole('button', { name: 'Login' })).toBeVisible();

    // Nuur huudasnii URL ruu butsasan esehiig shalgana
    await expect(page).toHaveURL('https://www.saucedemo.com/');
});
