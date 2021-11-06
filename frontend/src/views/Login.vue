<template>
    <v-container fluid fill-height class="justify-center">
        <v-card width="600">
            <v-card-title>Login</v-card-title>
            <v-card-text>
                <v-form
                    @submit.prevent="login"
                >
                    <v-row>
                        <v-col>
                            <v-text-field
                                outlined
                                dense
                                label="Usuario"
                                v-model="username"
                            >
                            </v-text-field>
                            <v-text-field
                                type="password"
                                outlined
                                dense
                                label="Contraseña"
                                v-model="password"
                            >
                            </v-text-field>
                        </v-col>
                        <v-col class="col-5 d-flex justify-center align-center">
                            <div>
                                <v-btn
                                    type="submit"
                                    color="primary"
                                    block
                                >
                                    Iniciar sesion
                                </v-btn>
                                <p class="mt-2">¿No tenés cuenta? <router-link class="text-decoration-none" to="/registrar">Crear cuenta</router-link></p>
                            </div>
                        </v-col>
                    </v-row>
                </v-form>
            </v-card-text>
            <v-overlay
                :value="loading"
                absolute
            >
                <v-progress-circular
                    indeterminate
                ></v-progress-circular>
            </v-overlay>
        </v-card>
        <v-snackbar
            v-model="error"
            :timeout="2000"
            top
            color="error darken-3"
        >
            {{ error_message }}
        </v-snackbar>
    </v-container>
</template>

<script>
import RequestHelper from "@/utils/RequestHelper";

export default {
    name: "Login",
    data: () => ({
        loading: false,
        error: false,
        error_message: "",
        username: "",
        password: ""
    }),
    methods: {
        login() {
            this.loading = true;

            const request = {
                url: "/api/auth/login",
                body: {
                    username: this.username,
                    password: this.password
                },
                handler: {
                    "200": (response) => {
                        let token = response.data.token;
                        this.$store.commit('setToken', token);
                        this.$router.push("/");
                    },
                    "422": (response) => {
                        let message = response.data.message;
                        this.error = true;
                        this.error_message = message;
                    },
                    default: (response) => {
                        console.log("default", response)
                    },
                    error: (response) => {
                        let message = response.data.message;
                        console.log(message)
                    },
                    always: () => {
                        this.loading = false;
                    }
                }
            };
            RequestHelper.post(request);
        }
    }
}
</script>

<style scoped>

</style>