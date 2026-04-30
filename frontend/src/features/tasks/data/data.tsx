import {
  ArrowDown,
  ArrowRight,
  ArrowUp,
  Circle,
  CheckCircle,
  AlertCircle,
  Timer,
  HelpCircle,
  CircleOff,
} from 'lucide-react'

export const labels = [
  {
    value: 'bug',
    label: 'Error',
  },
  {
    value: 'feature',
    label: 'Característica',
  },
  {
    value: 'documentation',
    label: 'Documentación',
  },
]

export const statuses = [
  {
    label: 'Pendiente',
    value: 'backlog' as const,
    icon: HelpCircle,
  },
  {
    label: 'Por hacer',
    value: 'todo' as const,
    icon: Circle,
  },
  {
    label: 'En Progreso',
    value: 'in progress' as const,
    icon: Timer,
  },
  {
    label: 'Completado',
    value: 'done' as const,
    icon: CheckCircle,
  },
  {
    label: 'Cancelado',
    value: 'canceled' as const,
    icon: CircleOff,
  },
]

export const priorities = [
  {
    label: 'Baja',
    value: 'low' as const,
    icon: ArrowDown,
  },
  {
    label: 'Media',
    value: 'medium' as const,
    icon: ArrowRight,
  },
  {
    label: 'Alta',
    value: 'high' as const,
    icon: ArrowUp,
  },
  {
    label: 'Crítica',
    value: 'critical' as const,
    icon: AlertCircle,
  },
]
