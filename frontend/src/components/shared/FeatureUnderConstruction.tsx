import { Card, CardContent, CardHeader, Typography, Box, alpha } from '@mui/material';
import { Wrench } from 'lucide-react';

interface FeatureUnderConstructionProps {
  readonly featureName: string;
  readonly phase: number;
}

export const FeatureUnderConstruction = ({ featureName, phase }: FeatureUnderConstructionProps) => {
  return (
    <Box
      sx={{
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
        width: '100%',
        p: 4,
      }}
    >
      <Card
        variant="outlined"
        sx={(theme) => ({
          maxWidth: '480px',
          width: '100%',
          borderColor: alpha(theme.palette.warning.main, 0.5),
          backgroundColor: alpha(theme.palette.warning.main, 0.05),
          borderStyle: 'dashed',
          borderWidth: 2,
        })}
      >
        <CardHeader
          avatar={
            <Box
              sx={(theme) => ({
                backgroundColor: alpha(theme.palette.warning.main, 0.2),
                borderRadius: '50%',
                p: 1.5,
                display: 'flex',
              })}
            >
              <Wrench />
            </Box>
          }
          title={
            <Typography variant="h6" component="h2" sx={{ fontWeight: 600, color: 'warning.dark' }}>
              Funcionalidade em Construção
            </Typography>
          }
        />
        <CardContent>
          <Typography variant="body1" sx={{ color: 'text.primary' }}>
            A funcionalidade de{' '}
            <Typography component="strong" sx={{ fontWeight: 'medium', color: 'text.primary' }}>
              {featureName}
            </Typography>{' '}
            está no nosso roteiro e será implementada na Fase {phase} do projeto.
          </Typography>
          <Typography variant="body2" sx={{ mt: 1.5, color: 'text.secondary' }}>
            Agradecemos a sua paciência enquanto continuamos a construir a melhor ferramenta para o seu controle financeiro!
          </Typography>
        </CardContent>
      </Card>
    </Box>
  );
};