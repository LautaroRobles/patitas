<template>
    <v-container>
        <v-row class="mt-2">
            <v-col>
                <v-card>
                    <v-card-title>Titulo de carta</v-card-title>
                    <v-card-text>Un poco de texto</v-card-text>
                    <v-card-actions>
                        <v-btn color="primary">accionar</v-btn>
                        <v-btn text color="primary">salir</v-btn>
                    </v-card-actions>
                </v-card>
            </v-col>
            <v-col>
                <v-card>
                    <v-card-title>Titulo de carta</v-card-title>
                    <v-card-text>Un poco de texto</v-card-text>
                    <v-card-actions>
                        <v-btn text color="primary">accionar</v-btn>
                    </v-card-actions>
                </v-card>
            </v-col>
        </v-row>
        <v-row>
            <v-col>
                <v-card>
                    <v-card-title>Titulo de carta</v-card-title>
                    <v-card-text>Un poco de texto</v-card-text>
                    <v-card-actions>
                        <v-btn text color="primary">accionar</v-btn>
                    </v-card-actions>
                </v-card>
            </v-col>
        </v-row>
        <qr-code text="google.com"></qr-code>
    </v-container>
</template>

<script>
export default {
}
</script>

<style>

</style>